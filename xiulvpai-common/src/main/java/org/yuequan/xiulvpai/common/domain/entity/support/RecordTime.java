package org.yuequan.xiulvpai.common.domain.entity.support;

import java.util.Date;

/**
 * 记录时间
 * @author yuequan
 * @since 1.0
 **/
public interface RecordTime {
    /**
     * 设置创建时间
     * @param date
     */
   void setCreatedAt(Date date);

    /**
     * 设置更新时间
     * @param date
     */
   void setUpdatedAt(Date date);

    /**
     * 获取创建时间
     * @return createdAt
     */
   Date getCreatedAt();

    /**
     * 获取更新时间
     * @return updatedAt
     */
   Date getUpdatedAt();
}
