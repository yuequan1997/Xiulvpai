package com.yuequan.xiulvpai.common.domain.entity.support;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * 记录时间监听器
 * @author yuequan
 * @since 1.0
 **/
public class RecordTimeListener {
    @PreUpdate
    @PrePersist
    public void record(Object object){
        if(object instanceof RecordTime){
            var recordTime = RecordTime.class.cast(object);
            if(recordTime.getCreatedAt() == null){
                recordTime.setCreatedAt(new Date());
            }
            recordTime.setUpdatedAt(new Date());
        }
    }
}
