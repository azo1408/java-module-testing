/*
 * Copyright 2022 the GradleX team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradlex.javamodule.testing.internal.bridges;

import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import org.gradle.api.provider.Provider;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.compile.JavaCompile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaModuleDependenciesBridge {

    public static Provider<?> gav(Project project, String moduleName) {
        Object javaModuleDependencies = project.getExtensions().findByName("javaModuleDependencies");
        if (javaModuleDependencies == null) {
            return null;
        }
        try {
            Method gav = javaModuleDependencies.getClass().getMethod("gav", String.class);
            return (Provider<?>) gav.invoke(javaModuleDependencies, moduleName);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileCollection addRequiresRuntimeSupport(Project project, JavaCompile task, SourceSet sourceSet) {
        Object javaModuleDependencies = project.getExtensions().findByName("javaModuleDependencies");
        if (javaModuleDependencies == null) {
            return project.getObjects().fileCollection();
        }
        try {
            Method gav = javaModuleDependencies.getClass().getMethod("addRequiresRuntimeSupport", JavaCompile.class, SourceSet.class);
            return (FileCollection) gav.invoke(javaModuleDependencies, task, sourceSet);
        } catch (NoSuchMethodException e) {
            return project.getObjects().fileCollection();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}