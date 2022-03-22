package com.atguigu.Import.MyImportSelector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

//自定义逻辑，返回需要导入的组件
public class MyImportSelecor implements ImportSelector {
    /**
     *
     * @param importingClassMetadata 当前标注@Import注解的类的所有注解信息
     * @return  不能返回null否则会报空指针异常
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.atguigu.Import.Blue","com.atguigu.Import.Yellow"};
    }
}
