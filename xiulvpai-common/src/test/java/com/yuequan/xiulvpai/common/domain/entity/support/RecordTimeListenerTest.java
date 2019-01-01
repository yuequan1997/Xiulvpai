package com.yuequan.xiulvpai.common.domain.entity.support;

import com.yuequan.xiulvpai.common.CommonApplicationTest;
import com.yuequan.xiulvpai.common.domain.entity.RecordTimeEntity;
import com.yuequan.xiulvpai.common.repository.RecordTimeEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
@SpringBootTest(classes = CommonApplicationTest.class)
public class RecordTimeListenerTest {

    @Autowired
    private RecordTimeEntityRepository recordTimeRepository;

    @Test
    public void record() {
        RecordTimeEntity recordTimeEntity = new RecordTimeEntity();
        recordTimeRepository.save(recordTimeEntity);

        assertNotNull(recordTimeEntity.getCreatedAt());
        assertNotNull(recordTimeEntity.getUpdatedAt());
    }
}