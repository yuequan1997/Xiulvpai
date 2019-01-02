package org.yuequan.xiulvpai.web.factory;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.Charset.forName;

/**
 * @author yuequan
 * @since
 **/
public class BeanFactory {
    public final static EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(123L)
            .objectPoolSize(100)
            .randomizationDepth(3)
            .charset(forName("UTF-8"))
            .stringLengthRange(4, 15)
            .collectionSizeRange(1, 10)
            .scanClasspathForConcreteTypes(true)
            .overrideDefaultInitialization(false)
            .build();

    protected static final <T> T get(Class<T> clazz, String... exclude){
        var object = random.nextObject(clazz, exclude);
        return object;
    }


    protected static final <T> List<T> get(int number, Class<T> clazz, String... exclude){
        var objects = new ArrayList<T>(number);
        for (int i = 0; i < number; i++) {
            objects.add(get(clazz, exclude));
        }
        return objects;
    }
}
