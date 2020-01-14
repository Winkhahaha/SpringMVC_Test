package test.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/*
自定义逻辑,判断要导入到容器中的组件
AnnotationMetadata:当前标注@Import注解的类的所有注解信息
 */
public class MyImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 返回需要导入的组件的全类名数组,而不是导入该类
        return new String[]{"test.entity.Blue","test.entity.Green"};
    }
}
