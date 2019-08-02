# HolderView
用于加载过程、加载异常、加载失败、空数据的占位布局

<a href="https://github.com/kongzue/HolderView/">
<img src="https://img.shields.io/badge/HolderView-1.0.0-green.svg" alt="Kongzue HolderView">
</a>
<a href="https://bintray.com/myzchh/maven/HolderView">
<img src="https://img.shields.io/badge/Maven-1.0.0-blue.svg" alt="Maven">
</a>
<a href="http://www.apache.org/licenses/LICENSE-2.0">
<img src="https://img.shields.io/badge/License-Apache%202.0-red.svg" alt="License">
</a>
<a href="http://www.kongzue.com">
<img src="https://img.shields.io/badge/Homepage-Kongzue.com-brightgreen.svg" alt="Homepage">
</a>

![HolderView](https://github.com/kongzue/Res/raw/master/app/src/main/res/mipmap-xxxhdpi/img_holder_view.png)

Demo下载：<https://fir.im/HolderView>

## 引入
1) 从 Maven 仓库或 jCenter 引入：
Maven仓库：
```
<dependency>
  <groupId>com.kongzue.view</groupId>
  <artifactId>HolderView</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
Gradle：
在dependencies{}中添加引用：
```
implementation 'com.kongzue.view:HolderView:1.0.0'
```

## 使用方法
HolderView 类似于 RelativeLayout，你只需要将自己的布局放入其中即可：
```
<com.kongzue.holderview.HolderView
        android:id="@+id/holdView"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        
        <!--在此处放入你的布局-->
        
</com.kongzue.holderview.HolderView>
```

HolderView 提供加载过程、加载异常、加载失败、空数据的占位布局，使用以下方法即可调用：
```
//显示正在加载样式
holdView.showWait();

//显示网络异常提示
holdView.showBadNet();

//显示数据加载错误提示
holdView.showError();

//显示空数据提示
holdView.showEmpty();

//显示完成（即关闭所有提示，显示你的布局）
holdView.showSuccess();
```

## 自定义
在 XML 布局中自定义 HolderView：
```
<com.kongzue.holderview.HolderView xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/holdView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:badNetResId="@mipmap/img_error_net"
    app:badNetText="你家网线被猫抓掉啦！"
    app:emptyImageResId="@mipmap/img_empty_data"
    app:emptyText="没啥可看的啊"
    app:errorImageResId="@mipmap/img_error_data"
    app:errorText="服务器炸啦！"
    app:retryButtonText="再试一次"
    app:retryButtonVisibility="true"
    app:themeColor="@color/holderColor">
        
    <!--在此处放入你的布局-->
    
</com.kongzue.holderview.HolderView>
```

属性对应说明：

字段 | 含义 | 类型
---|---|---
app:badNetResId  | 网络错误时图片资源  | ResInt
app:badNetText  | 网络错误时提示文字  | String
app:emptyImageResId  | 空数据时图片资源  | ResInt
app:emptyText  | 空数据时提示文字  | String
app:errorImageResId  | 加载错误时图片资源  | ResInt
app:errorText  | 加载错误时提示文字  | String
app:retryButtonText  | 重试按钮文本  | String
app:retryButtonVisibility  | 是否显示重试按钮  | boolean
app:themeColor  | 主体颜色  | ColorInt

## 开源协议
```
Copyright Kongzue HolderView

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## 更新日志
v1.0.0:
- 全新发布；