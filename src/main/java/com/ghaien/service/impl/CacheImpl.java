package com.ghaien.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.ghaien.dao.RedisDao;
import com.ghaien.service.ICache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by ghaien on 2018/1/30.
 */
@Component
public class CacheImpl implements ICache {

    @Autowired
    private RedisDao redisDao;

    /********* (1)、Key（键）****************************************/
    @Override
    public Boolean exists(String key) {
        return redisDao.exists(key);
    }

    @Override
    public void delete(String key) {
        redisDao.delete(key);
    }

    @Override
    public void delete(List keys){
        redisDao.delete(keys);
    }

    @Override
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisDao.expire(key, timeout, unit);
    }

    @Override
    public Long getExpire(String key) {
        return redisDao.getExpire(key);
    }

    @Override
    public Long getExpire(String key, TimeUnit unit){
        return redisDao.getExpire(key, unit);
    }

    @Override
    public void rename(String key, String newKey){
        redisDao.rename(key, newKey);
    }

    @Override
    public String type(String key){
        return redisDao.type(key);
    }

    /**
     * 模糊查询key
     * @param pattern
     * @return
     */
    public Set<String> findKeys(String pattern){
        if(StringUtils.isEmpty(pattern)){
            return null;
        }

        return redisDao.findKeys(pattern);
    }

    /********* (2)、String（字符串）  ****************************************/
    @Override
    public long increment(String key, long offset) {
        return redisDao.increment(key, offset);
    }

    /*-------------valueOps put ------------*/
    @Override
    public void put(String key, Object value) {
        redisDao.put(key, this.ObjectToString(value));
    }

    @Override
    public void put(String key, Object value, long timeout, TimeUnit unit) {
        if(timeout>0){
            redisDao.put(key, this.ObjectToString(value), timeout, unit);
        }else{
            redisDao.put(key, this.ObjectToString(value));
        }
    }

    @Override
    public void putKeepTTL(String key, Object value) {

        redisDao.putKeepTTL(key, this.ObjectToString(value));
    }

    @Override
    public void putMulti(Map<String, Object> keyValue) {
        if (keyValue.isEmpty()) {
            return;
        }
        final Map<String, String> data = new LinkedHashMap<String, String>(keyValue.size());
        for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
            data.put(entry.getKey(), this.ObjectToString(entry.getValue()));
        }
        redisDao.putMulti(data);
    }

    @Override
    public Boolean putIfAbsent(String key, Object value) {
        return redisDao.putIfAbsent(key, this.ObjectToString(value));
    }

    @Override
    public Boolean putMultiIfAbsent(Map<String, Object> keyValue) {
        if (keyValue.isEmpty()) {
            return true;
        }
        final Map<String, String> data = new LinkedHashMap<String, String>(keyValue.size());
        for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
            data.put(entry.getKey(), this.ObjectToString(entry.getValue()));
        }

        return redisDao.putMultiIfAbsent(data);
    }

    /*-------------valueOps get ------------*/
    @Override
    public String get(String key) {
        return redisDao.get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getMulti(Collection<String> keys) {

        return redisDao.getMulti(keys);
    }

    /********* (3)、Hash（哈希表 ） ****************************************/

    @Override
    public Long hashSize(String key){
        return redisDao.hashSize(key);
    }

    @Override
    public Map<String, String> hashEntries(String key){
        return redisDao.hashEntries(key);
    }

    @Override
    public Set<String> hashKeys(String key){
        return redisDao.hashKeys(key);
    }

    @Override
    public List<String> hashValues(String key){
        return redisDao.hashValues(key);
    }

    @Override
    public Boolean hashHasKey(String key, String hashKey){
        return redisDao.hashHasKey(key, hashKey);
    }

    @Override
    public void hashDelete(String key, String hashKey){
        redisDao.hashDelete(key, hashKey);
    }

    @Override
    public String hashGet(String key, String hashKey){
        return redisDao.hashGet(key, hashKey);
    }

    @Override
    public List<String> hashGetMulti(String key, Collection<String> hashKeys){
        return redisDao.hashGetMulti(key, hashKeys);
    }

    @Override
    public void hashPut(String key, String hashKey, Object value){
        redisDao.hashPut(key, hashKey, this.ObjectToString(value));
    }

    @Override
    public Boolean hashPutIfAbsent(String key, String hashKey, Object value){
        return redisDao.hashPutIfAbsent(key, hashKey, this.ObjectToString(value));
    }

    @Override
    public void hashPutAllOfString(String key, Map<String,String> valueMap){
        redisDao.hashPutAllOfString(key, valueMap);
    }

    @Override
    public Long hashIncrBy(String key, String hashKey, Long increment) {

        return redisDao.hashIncrBy(key, hashKey, increment);
    }

    @Override
    public void hashPutAllOfVo(String key, Map<String,Object> valueMap){
        Map<String,String> dataMap = new HashMap<String,String>();
    	/*Set<String> set = valueMap.keySet();
    	Iterator<String> it = set.iterator();
    	while(it.hasNext()) {
    		dataMap.put(it.next(), JSONUtils.objToJson(valueMap.get(it.next())));
    	}*/
        for (String mapKey : valueMap.keySet()) {
            dataMap.put(mapKey,this.ObjectToString(valueMap.get(mapKey)));
        }
        redisDao.hashPutAllOfString(key, dataMap);
    }


    /********* (4)、List（列表）*****************************************/
    /**
     * 同方向进出就是----堆栈操作
     * 不同方向进出就是----队列操作
     * 这里只提供：左进、左出、右出
     */

    @Override
    public Long listSize(String key) {
        return redisDao.listSize(key);
    }

    @Override
    public void listSetIndex(String key, long index, Object value) {
        redisDao.listSetIndex(key, index, this.ObjectToString(value));
    }

    @Override
    public Long listRemove(String key, long i, Object value) {
        return redisDao.listRemove(key, i, this.ObjectToString(value));
    }

    @Override
    public void listTrim(String key, long start, long end) {
        redisDao.listTrim(key, start, end);
    }

    @Override
    public List<String> listRange(String key, long start, long end) {
        return redisDao.listRange(key, start, end);
    }

    @Override
    public Long listLeftPush(String key, Object value) {
        return redisDao.listLeftPush(key, this.ObjectToString(value));
    }

    @Override
    public Long listLeftPushIfPresent(String key, Object value) {
        return redisDao.listLeftPushIfPresent(key, this.ObjectToString(value));
    }

    @Override
    public String listLeftPop(String key) {
        return redisDao.listLeftPop(key);
    }

    @Override
    public String listRightPop(String key) {
        return redisDao.listRightPop(key);
    }

    @Override
    public String listRightPopAndLeftPush(String key, String newKey) {
        return redisDao.listRightPopAndLeftPush(key, newKey);
    }

    @Override
    public String listRightPopAndLeftPush(String key, String newKey, long timeout, TimeUnit unit) {
        return redisDao.listRightPopAndLeftPush(key, newKey, timeout, unit);
    }


    /********* (5)、Set（集合）****************************************/

    @Override
    public Long setSize(String key){
        return redisDao.setSize(key);
    }

    @Override
    public void setAdd(String key, Object obj) {
        redisDao.setAdd(key, this.ObjectToString(obj));
    }

    @Override
    public void setAdd(String key, List<Object> valueList) {
        for (Object value : valueList) {
            redisDao.setAdd(key,this.ObjectToString(value));
        }
    }

    @Override
    public List<String> setMembers(String key) {
        List<String> valueList = new ArrayList<String> ();
        Set<String> valueSet = redisDao.setMembers(key);
        valueList.addAll(valueSet);
        return valueList;
    }

    @Override
    public Boolean setIsMember(String key, Object obj){
        return redisDao.setIsMember(key, this.ObjectToString(obj));
    }

    @Override
    public Long setRemove(String key, Object obj) {
        return redisDao.setRemove(key, this.ObjectToString(obj));
    }


    /********* (6)、ZSet（有序集合）****************************************/

    @Override
    public Long zsetSize(String key) {
        return redisDao.zsetSize(key);
    }

    @Override
    public void zsetClear(String key) {
        redisDao.zsetClear(key);
    }

    @Override
    public void zsetAdd(String key, String obj, double score) {
        redisDao.zsetAdd(key, obj, score);
    }

    @Override
    public void zsetAdd(String key, Set<ZSetOperations.TypedTuple<String>> tuples) {
        redisDao.zsetAdd(key, tuples);
    }

    @Override
    public Set<String> zsetGetRange(String key, long start, long end) {
        return redisDao.zsetGetRange(key, start, end);
    }

    @Override
    public Set<String> zsetGetRevRange(String key, long start, long end) {
        return redisDao.zsetGetRevRange(key, start, end);
    }

    @Override
    public Long zsetRemove(String key, Object obj){
        return redisDao.zsetRemove(key, this.ObjectToString(obj));
    }

    @Override
    public Long zsetRank(String key, Object obj) {
        return redisDao.zsetRank(key, this.ObjectToString(obj));
    }

    @Override
    public Double zsetScore(String key, Object obj) {
        return redisDao.zsetScore(key, this.ObjectToString(obj));
    }

    @Override
    public Double zsetIncrementScore(String key, Object obj, double incrementScore) {
        return redisDao.zsetIncrementScore(key, this.ObjectToString(obj), incrementScore);
    }

    @Override
    public Long listRightPush(String key, Object value) {
        return redisDao.listRightPush(key, this.ObjectToString(value));
    }

    @Override
    public Boolean lock(String key) {
        return this.lock(key,60l);
    }

    @Override
    public Boolean lock(String key, Long timeOut) {
        return redisDao.lock(key,timeOut);
    }

    @Override
    public void unlock(String key) {
        redisDao.unLock(key);
    }

    public void setRedisDao(RedisDao redisDao) {
        this.redisDao = redisDao;
    }

    private String ObjectToString(Object obj){
        String val = "";
        if(obj instanceof String){
            val = (String) obj;
        }else{
            val = JSONUtils.toJSONString(obj);
        }
        return val;
    }
}
