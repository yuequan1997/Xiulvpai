package org.yuequan.xiulvpai.common.domain.entity;

import org.yuequan.xiulvpai.common.domain.entity.support.RecordTime;
import org.yuequan.xiulvpai.common.domain.entity.support.RecordTimeListener;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuequan
 * @since 1.0
 */

@Entity
@Table(name = "record_time")
@Data
@EntityListeners({RecordTimeListener.class})
public class RecordTimeEntity implements RecordTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
}
