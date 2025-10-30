###########################################################
# Widget API Consumer ProGuard Rules
###########################################################
# 这些规则会被自动应用到所有使用 widget-api 的模块
# 确保动态加载的插件能找到期望的类和方法

# ========================================================================
# 完全保护所有公开 API 类 - 禁止混淆、优化、访问修饰符修改
# ========================================================================
-keep public class com.neta.isulewtools.api.widget.** { *; }
-keep public interface com.neta.isulewtools.api.widget.** { *; }
-keep public enum com.neta.isulewtools.api.widget.** { *; }

# ========================================================================
# Kotlin Data Class 完整保护（关键！）
# ========================================================================
# 保护所有构造函数（包括主构造函数）
-keepclassmembers class com.neta.isulewtools.api.widget.** {
    public <init>(...);
}

# 保护 Kotlin 默认参数的合成构造函数
# 签名：<init>(实际参数..., int mask, DefaultConstructorMarker marker)
-keepclassmembers class com.neta.isulewtools.api.widget.** {
    <init>(*, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, *, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, *, *, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, *, *, *, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, *, *, *, *, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, *, *, *, *, *, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, *, *, *, *, *, *, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
    <init>(*, *, *, *, *, *, *, *, *, *, int, kotlin.jvm.internal.DefaultConstructorMarker);
}

# 保护 data class 的 copy 和 componentN 方法
-keepclassmembers class com.neta.isulewtools.api.widget.** {
    public ** copy(...);
    public ** component*();
}

# ========================================================================
# 禁止修改类名和包名（动态加载必需）
# ========================================================================
-keepnames class com.neta.isulewtools.api.widget.** { *; }
-keeppackagenames com.neta.isulewtools.api.widget.**

# ========================================================================
# Compose 相关（防止内联优化）
# ========================================================================
-keepclassmembers class com.neta.isulewtools.api.widget.** {
    @androidx.compose.runtime.Composable <methods>;
}

# ========================================================================
# 保护所有字段和方法签名
# ========================================================================
-keepclassmembernames class com.neta.isulewtools.api.widget.** {
    *;
}

# ========================================================================
# 保护泛型签名（反射可能需要）
# ========================================================================
-keepattributes Signature
#-keepattributes *Annotation*
-keepattributes InnerClasses
-keepattributes EnclosingMethod
