# 1. 常用 Java 关键字

## 1.1 Java 高频使用关键字 Top 20

在日常开发中，这些关键字出现频率最高，是你写代码时最常用的：

### 1.1.1 访问控制 & 类/方法修饰符

- **public**：几乎所有对外暴露的类、方法、变量都会用到
- **private**：封装内部状态，是面向对象编程的核心
- **static**：静态方法、静态变量、静态代码块，工具类和单例模式必备
- **final**：常量、不可变类、防止方法被重写，非常常用。定义 Java 常量语法：`final int PI = 3;`
- **class**：定义类的基础关键字，每个类都要写

### 1.1.2 程序控制语句

- **if / else**：条件判断的核心，无处不在
- **for / while**：循环结构，遍历数据、重复逻辑必备
- **return**：方法返回值，每个有返回值的方法都要用到
- **break / continue**：控制循环流程，高频出现
- **switch / case**：多分支选择，处理固定选项时很方便

### 1.1.3 基本类型与变量引用

- **int**：最常用的整数类型
- **String**（虽不是关键字，但和 `int` 一样高频）：字符串处理
- **this**：引用当前对象实例，构造器、成员方法中频繁出现
- **super**：调用父类构造器或方法，继承场景下常用。子类使用 `super`，就相当于调用了父类的构造函数
- **void**：无返回值方法的声明，大量工具方法使用

### 1.1.4 异常处理 & 包相关

- **try / catch / finally**：异常捕获处理，保证程序健壮性
- **throws**：方法声明可能抛出的异常，和 checked exception 绑定
- **import / package**：组织代码结构，每个 Java 文件开头都有

## 1.2 按使用场景排序（从高到低）

1. **基础语法类**：`class`、`public`、`private`、`static`、`if`、`else`、`for`、`return`、`this`、`void`
2. **流程控制类**：`while`、`break`、`continue`、`switch`、`case`
3. **工程化类**：`package`、`import`、`try`、`catch`、`final`

## 1.3 补充说明

- **`null`** **不是关键字**：它是字面常量，和 `true` / `false` 一样，不能作为标识符
- **`const`** **和** **`goto`**：是保留关键字，但 Java 中不允许使用，几乎不会遇到
- **`abstract`** **/** **`interface`** **/** **`implements`**：在接口和抽象类设计中高频，业务代码中稍少
- **`@Override`** 只是一个辅助注解，不会影响方法重写的功能。只要方法签名正确，有没有它，重写都会生效。

***

# 2. 谈一谈 Java 中访问控制修饰符

- **public**：所有类都可以访问
- **private**：只能在当前类中访问。目的是为了封装内部状态，防止外部直接访问和修改。
- **default**：在同一个包中可以访问，不同包中不能访问。`default` 修饰符的设计初衷是**包内封装**，只允许同一包内的类访问，对外部包隐藏，这是一种更严格的封装机制。
- **protected**：在同一个包中可以访问，不同包中对应子类可以访问，可以被子类继承。设计目的是**支持继承**，即使子类在不同的包中，也需要能够访问父类的某些成员来实现继承和重写。

## 2.1 关键区别

| 修饰符         | 同一包内 | 不同包的子类 | 不同包的非子类 | 能否被继承 |
| ----------- | ---- | ------ | ------- | ----- |
| `private`   | ✅    | ❌      | ❌       | ❌     |
| `default`   | ✅    | ❌      | ❌       | ✅     |
| `protected` | ✅    | ✅      | ❌       | ✅     |
| `public`    | ✅    | ✅      | ✅       | ✅     |

***

# 3. 谈一谈 Java 中非访问控制修饰符

- **static** 修饰符，用来修饰类方法和类变量（静态方法、静态变量）
  - **静态方法**：用于通用工具类的纯计算功能，像 C 语言的函数一样，其调用方法是：`类名.方法名()`
  - **非静态方法**：用于描述当前对象的独有信息，其调用方法是：`对象.方法名()`
  - 方法里出现了 `this.变量` / 成员变量 → ✅ 必须用**非静态方法**
  - 方法里只有参数、计算、循环，没用到对象属性 → ✅ 建议用**静态方法**
  - 静态方法不能直接调用非静态变量 / 方法
  - 静态方法只能调用静态变量 / 方法
  - 非静态方法可以随便调用静态内容
- **final** 修饰符，用来修饰类、方法和变量。`final` 修饰的类不能够被继承，修饰的方法不能被继承类重新定义，修饰的变量为常量，是不可修改的。
- **abstract** 修饰符，用来创建抽象类和抽象方法。
- **synchronized** 和 **volatile** 修饰符，主要用于线程的编程。

***

# 4. 自动装箱与拆箱详解（包装类：用对象把基本数据类型包起来）

## 4.1 核心概念

- **自动装箱（Autoboxing）**：Java 编译器自动将基本数据类型转换为对应的包装类对象
- **自动拆箱（Unboxing）**：Java 编译器自动将包装类对象转换为对应的基本数据类型

## 4.2 为什么需要自动装箱/拆箱？

Java 中的某些功能（如泛型、集合框架）**只能处理对象类型**，而不能直接处理基本数据类型。自动装箱/拆箱让代码更简洁，无需手动转换。

## 4.3 基本类型与包装类对应关系

| 基本类型      | 包装类         |
| --------- | ----------- |
| `byte`    | `Byte`      |
| `short`   | `Short`     |
| `int`     | `Integer`   |
| `long`    | `Long`      |
| `float`   | `Float`     |
| `double`  | `Double`    |
| `char`    | `Character` |
| `boolean` | `Boolean`   |

## 4.4 示例分析

### 4.4.1 示例 1：直接赋值（自动装箱）

```java
Character ch = 'a';  
// 自动装箱：char → Character 相当于Character ch = new Character('a');
```

**底层实现**：

```java
Character ch = Character.valueOf('a');  // 编译器自动生成
```

### 4.4.2 示例 2：方法参数传递（自动装箱）

```java
public void test(Character c) {
    // 方法体
}

// 调用时
test('x');  // 自动装箱：char → Character
```

### 4.4.3 示例 3：方法返回值（自动拆箱）

```java
public Character test(char c) {
    return c;  // 自动装箱：char → Character
}

// 接收返回值
char c = test('x');  // 自动拆箱：Character → char
```

## 4.5 实际应用场景

1. 集合框架
2. 泛型
3. 包装类的方法

## 4.6 底层实现原理

### 4.6.1 自动装箱的实现

对于 `Character` 类型，自动装箱使用 `Character.valueOf(char)` 方法：

```java
public static Character valueOf(char c) {
    if (c <= 127) { // 缓存常用字符
        return CharacterCache.cache[c];
    }
    return new Character(c);
}
```

### 4.6.2 自动拆箱的实现

对于 `Character` 类型，自动拆箱使用 `charValue()` 方法：

```java
public char charValue() {
    return value;
}
```

## 4.7 注意事项

### 4.7.1 性能影响

频繁的自动装箱/拆箱会创建大量临时对象，影响性能。在循环或频繁操作时应注意：

```java
// 不推荐：频繁装箱
for (int i = 0; i < 10000; i++) {
    list.add(i);  // 每次循环都装箱
}

// 推荐：手动控制
for (int i = 0; i < 10000; i++) {
    list.add(Integer.valueOf(i));  // 利用缓存
}
```

### 4.7.2 空指针风险

拆箱时如果包装类对象为 `null`，会抛出 `NullPointerException`：

```java
Character ch = null;
char c = ch;  // 运行时异常：NullPointerException
```

### 4.7.3 缓存机制

对于某些包装类，Java 会缓存常用值：

- `Character`：缓存 0\~127 的字符
- `Integer`：缓存 -128\~127 的整数

```java
Character c1 = 'a';  // 缓存
Character c2 = 'a';  // 缓存
System.out.println(c1 == c2);  // true（同一对象）

Character c3 = 'z';  // 缓存（'z' ASCII 码为 122）
Character c4 = 'z';  // 缓存
System.out.println(c3 == c4);  // true

Character c5 = (char) 128;  // 超出缓存范围
Character c6 = (char) 128;  // 超出缓存范围
System.out.println(c5 == c6);  // false（不同对象）
```

## 4.8 总结

| 操作   | 编译器行为              | 示例                                               |
| ---- | ------------------ | ------------------------------------------------ |
| 自动装箱 | 调用 `valueOf()` 方法  | `Character ch = 'a';` → `Character.valueOf('a')` |
| 自动拆箱 | 调用 `xxxValue()` 方法 | `char c = ch;` → `ch.charValue()`                |

自动装箱/拆箱是 Java 为了方便开发者而提供的语法糖，让代码更简洁易读，但在性能敏感的场景中需要注意其潜在影响。

***

# 5. Java String

## 5.1  最常用方法清单（按高频程度排序）

- **看长度/取字符**：`length()`、`charAt()`
- **比相等**：`equals()`、`equalsIgnoreCase()`
- **找位置/包含**：`indexOf()`、`lastIndexOf()`、`contains()`
- **切字符串**：`substring()`、`split()`
- **改内容**：`replace()`、`trim()`、`toLowerCase()` / `toUpperCase()`
- **判前后缀**：`startsWith()`、`endsWith()`
- **转类型**：`toCharArray()`、`valueOf()`、`getBytes()`
- **自己用过的**：`isEmpty()`、`trim()`
- **去重**：`distinct()`
- **筛选过滤**：`filter()`
- **映射转换**：`map()`
- **排序**：`sorted()`
- **收集结果**：`collect(Collectors.toList())`，也是终止操作

`Collectors` 是 Java 提供的**收集器工具类**，提供各种收集方式：

| 收集器            | 作用       | 示例                                  |
| -------------- | -------- | ----------------------------------- |
| `toList()`     | 收集成 List | `collect(Collectors.toList())`      |
| `groupingBy()` | 按条件分组收集  | `collect(Collectors.groupingBy())`  |
| `joining()`    | 连接成字符串   | `collect(Collectors.joining(", "))` |
| `counting()`   | 计数       | `collect(Collectors.counting())`    |

## 5.2 获取字符串对象的两种方式的区别

- **直接赋值（推荐）**：`String str1 = "hello"; String str2 = "hello";`
  - 内存执行过程：
    1. 执行 String str1 = "hello";
    - JVM 去常量池找 "hello"，发现没有
    - 在常量池中创建一个 "hello" 对象
    - 把常量池中 "hello" 的引用赋值给 str1
    1. 执行 String str2 = "hello";
    - JVM 再去常量池找 "hello"，发现已经存在
    - 直接返回常量池中同一个 "hello" 的引用赋值给 str2
  - 结果：str1 和 str2 指向常量池中的同一个对象，所以 `str1 == str2` 为 true（地址相同）；`str1.equals(str2)` 为 true（内容相同）
- **构造方法（不推荐）**：`String str1 = new String("hello"); String str2 = new String("hello");`
  - 内存执行过程：new String("hello")会执行两件事
    - 先处理括号里的字面量 "hello"：按照直接赋值的规则，检查常量池，没有就创建
    - 然后在堆内存中，创建一个全新的 String 对象，这个对象会引用常量池中的 "hello" 的字符数组
    - 把堆中这个新对象的引用赋值给变量
  - 所以：
    - 执行 String str1 时：如果常量池没有 "hello"，会创建2 个对象（常量池 1 个，堆 1 个）；如果已经有了，只创建1 个对象（堆 1 个）
    - 执行 String str2 时：常量池已经有 "hello" 了，只在堆中创建1 个新对象
  - 结果：
    - `str3 == str4` 为 false（地址不同，因为每次 new 都是新对象）
    - `str3.equals(str4)` 为 true（内容相同）

### 5.2.1 先搞懂核心：什么是字符串常量池？

字符串是 Java 中使用最频繁的对象，为了节省内存、提高性能，JVM 专门开辟了一块特殊的内存区域叫字符串常量池（JDK 7+ 位于堆内存中，JDK 6 及之前位于永久代）。

**核心规则：**

- 当程序中出现一个字符串字面量（比如 "hello"）时，JVM 会先去常量池查找是否已经存在这个字符串。
  - 如果存在：直接返回常量池中这个对象的引用，不创建新对象
  - 如果不存在：在常量池中创建这个字符串对象，然后返回它的引用

***

# 6. 符号说明

| 符号                | 解释                  | 示例     |
| ----------------- | ------------------- | ------ |
| `XXX.XX()`        | 调用 XXX 类的 XX 方法     | <br /> |
| `XXX::XX`         | `value -> XXX.XX()` | <br /> |
| `toXXX()`         | 收集成 XXX             | <br /> |
| `XXX.of(XX)`      | 创建 XX 对象的 XXX       | <br /> |
| `XXX.valueOf(XX)` | 将 XX 值转换成 XXX 值     | <br /> |
| `this.xx`         | 访问当前对象的属性 xx        | <br /> |
| `XX.toXXX()`      | 将 XX 转换成 XXX 类型的对象  | <br /> |

- System.out.println(对象) 等价于 System.out.println(对象.toString())

***

# 7. Stream、File、IO 知识点

作为**资深 Java 后端开发者**，给你一个**100% 明确、没有任何商量余地**的结论：

> **这三个知识点不仅要学，而且是 Java 后端开发的「吃饭家伙」，是面试必问、工作天天用的核心基础，优先级拉满。**

## 7.1 为什么必须学？（一句话讲透价值）

| 知识点              | 核心作用                     | 工作中每天用的场景                   | 面试必考程度        |
| ---------------- | ------------------------ | --------------------------- | ------------- |
| **IO 流**         | 程序和外界（文件、网络、内存）交换数据的唯一通道 | 文件读写、上传下载、网络通信、日志打印、序列化     | ⭐⭐⭐⭐⭐（100% 考） |
| **File / Files** | 操作本地文件和目录的工具             | 创建/删除文件、遍历文件夹、判断文件存在、获取文件属性 | ⭐⭐⭐⭐⭐（100% 考） |
| **Stream 流**     | 简化集合的批量处理，替代繁琐的 for 循环   | 过滤、排序、分组、统计、转换集合数据          | ⭐⭐⭐⭐⭐（100% 考） |

简单说：**不会这三个，你连一个最简单的后端接口都写不完整。**

> 对 AI 说："上述表格 + 精讲解一下，并为每一个知识点设置一个代码例子，并说一下面试的常用考法"，就可。

## 7.2 总结

这三个知识点是 Java 后端开发的**核心基础**，学习时建议：

1. **IO 流**：先掌握字节流/字符流 + 缓冲流的基础用法，再深入序列化、NIO
2. **File / Files**：优先学 `Files` 类（现代开发主流），`File` 类了解基础即可
3. **Stream**：多练常用操作（过滤、转换、分组、收集），结合实际业务场景使用

***

# 8. `this` VS `super`

## 8.1 `this` 和 `super` 核心区别对比表

| 维度         | `this`       | `super`      |
| ---------- | ------------ | ------------ |
| 指向对象       | 当前类的对象       | 当前对象的父类部分    |
| 调用构造方法     | 调用本类的其他构造方法  | 调用父类的构造方法    |
| 访问成员       | 访问本类的成员变量和方法 | 访问父类的成员变量和方法 |
| 本质         | 当前对象的引用      | 父类对象的引用      |
| 能否在静态方法中使用 | ❌ 不能         | ❌ 不能         |

## 8.2 面试必问的 5 个坑点（标准答案）

### ① `this()` 和 `super()` 能不能同时出现在同一个构造方法里？

**不能**。因为两者都要求必须写在构造方法的第一行，所以只能二选一。

### ② 为什么 `main` 方法里不能用 `this`？

因为 `main` 方法是静态方法，静态方法属于类，不属于任何对象。而 `this` 是对象的引用，没有对象就没有 `this`。

### ③ 子类构造方法为什么必须先调用父类构造方法？

因为子类继承了父类的成员，必须先让父类完成初始化，子类才能使用父类的成员。

### ④ `super` 调用的是父类的方法还是父类的对象？

`super` 调用的是**父类的方法**，而不是父类的对象。子类对象中只有一个对象，`super` 只是指向这个对象的父类部分。

### ⑤ 为什么 `this` 可以在成员方法中使用？

因为成员方法必须通过对象调用，调用时已经有了当前对象，所以 `this` 可以指向这个对象。

## 8.3 一句话总结

- `this`：找自己的东西，调用自己的构造
- `super`：找爸爸的东西，调用爸爸的构造
- **只要父类没有无参构造，子类的每一个构造方法，最终都必须通过某种方式（直接** **`super(参数)`** **或间接** **`this()`）调用到父类的有参构造。**

## 8.4 注意

- `User user = new User();` 表示创建了一个 User 对象，调用了无参构造方法 `new User()`，并返回一个 User 对象给 `user`。`user` 现在指向了一个新的 User 对象。
- 在继承关系中，如果你调用了子类的对象，那么父类的构造方法会自动调用，除非你显式调用了父类的构造方法。这时就会用上 `super` 关键字。

## 8.5 遇到的问题

### ① 假如父类中有多个有参构造函数，且它们没有通过 `this()` 链式调用，那么子类应该怎么使用 `super()`？

- `super(参数)` 可以调用父类的任意一个构造方法，只要参数列表（类型、数量、顺序）完全匹配即可。
- 父类的多个构造方法有没有通过 `this()` 链式调用，完全不影响子类的 `super()` 调用。子类只需要根据自己的业务需求，选择调用父类中参数匹配的那个构造就行。
- 所以不管父类有多少个构造方法，也不管它们之间有没有链式调用，子类只需要记住：**每个子类构造方法，最终必须通过** **`super(参数)`** **调用父类中参数匹配的一个构造方法，且只能调用一次。**

### ② 子类可以不调用父类的构造函数吗？

**Java 中没有任何办法可以让子类不调用父类的构造函数，这是 Java 语法的硬性强制规定。**

为什么绝对不能"不调用"？

- **核心原因**：子类继承了父类的所有成员。子类对象中包含了完整的父类对象。父类的成员变量和方法，必须先由父类的构造函数初始化完成，子类才能安全地使用它们。
- 如果允许子类不调用父类构造函数，那么父类的成员变量就会处于未初始化的状态，子类调用这些成员时会直接导致程序崩溃。
- **生活比喻**：你不能生一个没有爸爸的儿子。儿子的身体里一定包含了来自爸爸的基因，这些基因必须在儿子出生前就已经存在。

所以，Java 强制要求子类必须调用父类构造函数，这是为了保证对象的完整性和安全性。如果你觉得父类构造函数有问题，不要想着绕过它，而是去重构父类或者改用组合。

### ③ 假如子类有多个构造函数（它们没有通过 `this()` 链式调用），需要每一个子类都调用父类的某个构造函数吗？

**是的，必须每一个都调用。** 如果子类的多个构造函数没有通过 `this()` 链式调用，那么每一个构造函数都必须自己直接调用父类的某个构造函数，没有任何例外。

**为什么？**

- **核心原因：每个子类构造函数都会创建一个全新的独立对象。**
- 每个新的子类对象中，都包含一个完整的父类对象部分。这个父类部分必须由父类的构造函数初始化，**每个对象的父类部分都要单独初始化一次**。
- 不能说"我有一个构造函数调用过父类构造了，其他构造函数就不用了"——因为每个构造函数创建的是不同的对象，它们的父类部分是互相独立的。

***

# 9. Java 重写（Override）与重载（Overload）

## 9.1 总结

方法的重写（Overriding）和重载（Overloading）是 Java 多态性的不同表现：重写是父类与子类之间多态性的一种表现，重载可以理解成多态的具体表现形式。

- (1) **方法重载**：一个类中定义了多个方法名相同，而它们的参数的数量不同，或数量相同但类型和次序不同，则称为方法的重载（Overloading）。
- (2) **方法重写**：子类存在方法与父类的方法名字相同，而且参数的个数与类型一样，返回值也一样的方法，就称为重写（Overriding）。
- (3) **方法重载**是一个类的多态性表现，而**方法重写**是**子类与父类的一种多态性表现**。
- **重写（Override）**：绝对不能发生在同一个类中，只能发生在父子类中。
- **重载（Overload）**：不仅能发生在同一个类中，也能发生在父子类中。

## 9.2 重写（Override）

**记住：**

- 重写方法的异常，只能比父类的「更小、更少、没有」，不能「更大、更多」（仅限编译时异常）
- 子类重写方法的返回值，可以比父类的返回值"更小、更具体"，但不能更大
- 方法重写的核心规则可以总结为\*\*"两同两小一大"\*\*：
  - **两同**：方法名相同、参数列表相同
  - **两小**：返回值类型更小（或相同）、抛出的异常更小（或相同 / 没有）
  - **一大**：访问权限更大（或相同）
- 只要虚方法表里面的方法才能被重写，方法重写的实际意义就是替换了虚方法表中记录方法的内存地址。（只有非static、非final、非private的方法才可以进入虚方法表里，也就是说只有非static、非final、非private的方法才可以被重写）
- 有时 `父、子类方法/接口和类` 中有相同的方法名，但是如果添加了`@Override`之后会报错，不添加则不会报错，这是什么原因呢？=>此时就相当于接口和实现类里面刚好有两个同名的方法而已，但是不构成重写关系

### 关于 `Animal b = new Dog()`

- **原理**：让你用统一的父类视角去操作不同的子类对象，而实际行为由子类决定，这就是多态的本质。
- 这行代码做了两件事：
  - **编译时**：声明一个 `Animal` 类型的引用变量 `b`
  - **运行时**：在堆内存中创建一个 `Dog` 对象，并把引用赋给 `b`
- **所以**，`Animal b = new Dog()` 在声明这行代码之后：
  - 如果 `b` 调用一个方法，父、子都有相同的方法（重写），那么 `b` 就优先调用子类中的该方法
  - 如果 `b` 调用一个方法，父没有该方法但子有，那么在调用该方法时，必须将 `b` 强制类型转换为子类类型，才能调用该方法

## 9.3 重写与重载之间的区别

| 区别点  | 重载方法 | 重写方法                    |
| ---- | ---- | ----------------------- |
| 参数列表 | 必须修改 | 一定不能修改                  |
| 返回类型 | 可以修改 | 一定不能修改                  |
| 异常   | 可以修改 | 可以减少或删除，一定不能抛出新的或者更广的异常 |
| 访问   | 可以修改 | 一定不能做更严格的限制（可以降低限制）     |

## 9.4 `instanceof` 操作符

**作用**：检查左边变量「实际指向的堆内存中的对象」，是不是右边这个类（或它的子类、或实现了这个接口）的实例。它**完全不看左边变量的编译类型**，只看右边实际指向的对象是什么类型。

***

# 10. 枚举类 enum

## 10.1 疑惑点

### ① 对于抽象类和抽象方法的知识点：抽象类中不一定包含抽象方法，但是有抽象方法的类必定是抽象类。但是为什么枚举类中有抽象方法的类不是抽象类？

- 因为，枚举类的「抽象方法」，已经被它的每一个「枚举实例」（匿名子类）全部实现了，所以枚举类本身不需要是抽象类。
- 对于普通类如果有抽象方法，必须用 abstract 修饰，原因很简单：普通类的实例（用 new 创建的对象），无法实现抽象方法。如果允许普通类有抽象方法但不是抽象类，那么用 new 创建这个类的对象时，这个对象里就有一个 “未实现的空方法”，程序会崩溃。
- 普通类：有抽象方法必须是抽象类，因为普通类的 new 实例无法实现抽象方法枚举类：
- 有抽象方法不需要是抽象类，因为所有枚举实例（匿名子类）都已经实现了抽象方法，没有遗漏

### ② 枚举的每个实例，其实都是枚举类的匿名子类对象。所以如果枚举类有抽象方法，每个实例（匿名子类）都必须实现这个抽象方法。枚举常量在类加载时就一次性创建了所有常量，并且枚举类的构造函数调用次数等于枚举常量的数量（3 个常量 → 调用 3 次）。

***

# 11. 反射（Reflection）

**反射**：在运行时，程序可以获取到任意类的内部信息（如字段、方法、构造器等），并可以调用任意方法、访问任意字段。因此，反射不是"创建"一个类，而是"审视和操控"已存在的类。

## 11.1 应用场景

它是所有框架（Spring、MyBatis、Spring Boot）的底层核心，用于实现依赖注入、Bean 管理、ORM 等高级功能。

## 11.2 任何理解----看程序

```
import java.lang.reflect.Constructor; // 用于操作构造函数
import java.lang.reflect.Field; // 用来操作成员变量
import java.lang.reflect.Method; // 用来操作方法
public class test10 {
  public static void main(String[] args) throws Exception {
    // 获取 Person 类的 Class 对象。Class 对象是反射的入口，它包含了 Person 类的所有元信息（构造器、字段、方法等）。
    Class<?> clazz = Person.class; 
    // 获取 Person 类的参数为 (String, int) 的构造函数
    Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
    // 创建 Person 类的实例
    Object person = constructor.newInstance("John", 18);

    // 获取 Person 类的 name 字段
    Field nameField = clazz.getDeclaredField("name");
    // 绕过 private 限制
    nameField.setAccessible(true);
    // 读取 name 字段的值
    System.out.println("Name:" + nameField.get(person));

    // 修改 name 的值
    nameField.set(person, "Doe");
    // 读取修改后的 name 字段值
    System.out.println("Updated Name:" + nameField.get(person));

    // 获取 Person 类中名为 "greet"、参数类型为 String 的方法
    Method greetMethod = clazz.getMethod("greet", String.class);
    // 调用 greet 方法，等价于 person.greet("world");
    greetMethod.invoke(person, "world");
  }
}
class Person{
  private String name;
  private int age;

  public Person(String name, int age){
    this.name = name;
    this.age = age;
  }

  public void greet(String message){
    System.out.println(name + " says: " + message);
  }
}

```

***

# 12. 接口（Interface）

## 12.1 接口的定义

接口是一种特殊的类，它只包含方法的声明，不包含方法的实现。接口的定义用 interface 关键字，方法的声明用 abstract 关键字。

## 12.2 默认方法--JDK8新增

- 作用：为了接口升级而存在
- 格式：`public default 返回值类型 方法名(参数列表){...}`
- 注意事项：
  - 默认方法不是抽象方法，所以不强制被重写。但是如果被重写，重写的时候去掉default关键字
  - 在接口的默认方法中，public 可以被省略，default 不能被省略
  - 如果实现了多个接口，多个接口中存在相同名字的默认方法，子类就必须对该方法进行重写
  - 默认方法在被调用时，和普通方法没有区别，即对象名.方法名()

## 12.3 静态方法--JDK8新增

- 作用：为了接口的升级而存在
- 格式： `public static 返回值类型 方法名(参数列表){...}`
- 注意事项：
  - 接口里的静态方法只能通过接口名进行调用（接口名.方法名()），不能通过实现类的类名或对象名进行调用
  - 在接口的静态方法中，public 可以被省略，static 不能被省略
  - 如果类实现了接口，那么该类不能调用接口的静态方法，也不能使用@Override重写该静态方法，只能覆盖该静态方法

## 12.4 私有方法--JDK9新增

- ① 普通default的私有方法
  - 作用：为了抽取默认方法中重复代码而出现的
  - 格式：`private 返回值类型 方法名(参数列表){...}`
- ② 静态static的私有方法
  - 作用：为了抽取静态方法中重复代码而出现的
  - 格式：`private static 返回值类型 方法名(参数列表){...}`

## 12.5 总结

- JDK7：接口只能有抽象方法，没有方法体
- JDK8：接口中可以定义有方法体的方法。（默认、静态方法）
- JDK9：接口可以有私有方法（普通default的私有方法、静态static的私有方法），但是不能被实现类调用

***

# 13. 内部类（Inner Class）

## 13.1 Java 内部类示例（成员内部类、静态内部类、局部内部类、匿名内部类）

### 13.1.1. 成员内部类（最常用，会认就行）：写在成员位置的，属于外部类的成员

- 获取成员内部类对象的两种方法：
  - ① 当成员内部类被private修饰时，就应该在外部类中编写能够访问内部类的方法，对外提供内部类对象，即通过方法访问内部类对象

```java
class Outer{
  String name;
  private class Inner{}
  public Inner getInstance(){
    return new Inner();
  }
}
public class Test {
  public static void main(String[] args) {
    Outer o = new Outer();
    Object oi = o.getInstance();
    System.out.println(oi);
    // Outer.Inner oi2 = (Outer.Inner)oi; 此时报错
  }
}
```

- ② 当成员内部类被非private修饰时，直接创建对象就可以被访问，即`Outer.Inner inner = outer.new Inner();`
- 外部类成员变量和内部类成员变量重写时，在内部类如何访问外部类的成员变量？即`System.out.println(Outer.this.name);`。如果类中没有重复的成员变量，直接访问即可。

```java
class Outer{
  private int a = 10;
  class Inner{
    private int a = 20;
    public void show(){
      int a = 30;
      System.out.println(a); // 30
      System.out.println(new Outer().new Inner().a); //20
      System.out.println(this.a); //20
      System.out.println(new Outer().a); //10
      System.out.println(Outer.this.a); //10
    }
  }
}
public class Test {
    public static void main(String[] args) {
        // 创建内部类对象，需要先创建外部类
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.show();
        
        // 或者一步到位
        new Outer().new Inner().show();
    }
}
```

### 13.1.2. 静态内部类（会认就行）：静态内部类是一种特殊的成员内部类

- 创建静态内部类对象的方法：`Outer.Inner inner = new Outer.Inner();`
- 如何调用：
  - 假如该内部类里的是静态方法，则`外部类名.内部类名.方法名()`
  - 假如该内部类里的是非静态方法，则先创建静态内部类对象，再用这个对象进行调用

```java
class Outer {
    static String name = "外部类";
    
    static class Inner {
        public void show() {
            System.out.println("静态内部类的方法，访问外部类: " + name);
        }
    }
}

public class Test {
    public static void main(String[] args) {
        // 静态内部类可以直接创建，不需要外部类实例
        Outer.Inner inner = new Outer.Inner();
        inner.show();
    }
}
```

```java
class Outer{
  int a = 10;
  static int b = 20;

  static class Inner1{
    public void show1(){
      Outer o = new Outer();
      System.out.println(o.a);
      System.out.println(b);
    }
    public static void show2(){
      Outer o = new Outer();
      System.out.println(o.a);
      System.out.println(b);
    } 
  }
  class Inner2{
    public void show3(){
      System.out.println(a);
      System.out.println(b);
    }
    public static void show4(){
      Outer o = new Outer();
      System.out.println(o.a);
      System.out.println(b);
    } 
  }
}
public class test14{
  public static void main(String[] args) {
    new Outer.Inner1().show1(); // 此时创建的是Outer类里的Inner1类的实例
    Outer.Inner1.show2(); // 此时调用的是Outer类里的Inner1类的静态方法
    new Outer().new Inner2().show3(); // 属于成员内部类，需要先创建Outer类的实例，再创建Inner2类的实例
    Outer.Inner2.show4(); // 此时调用的是Outer类里的Inner2类的静态方法
  }
}
```

### 13.1.3. 局部内部类（方法内部）（会认就行）

- 将内部类定义在方法里面的就叫做局部内部类，类似于方法里的局部变量
- 外界是无法直接使用这个局部内部类的，需要在方法内部创建该内部类的对象并调用
- 该局部内部
- 类可以直接访问外部类的成员，也可以访问方法内的局部变量

```java
class Outer {
    public void method() {
        String localVar = "局部变量";
        
        // 在方法内部定义的类
        class Inner {
            public void show() {
                System.out.println("局部内部类，访问局部变量: " + localVar);
            }
        }
        
        // 只能在方法内创建和使用
        Inner inner = new Inner();
        inner.show();
    }
}
```

### 13.1.4. 匿名内部类（最常用、最重要）：隐藏名字的内部类，可以写在成员位置，也可以写在局部位置

- 匿名内部类的格式：`new 类名/接口名(){...}`
- 匿名内部类等价于：没有名字的Java类 + 实现接口/继承类 + 重写方法 + 创建对象
  - **没有名字的Java类**：大括号
  - **Swim**：表示这个没有名字Java类实现了一个Swim接口
  - **重写方法**：我们要在大括号当中重写Swim接口的所以方法
  - **创建对象**：new 关键词作用于后面这个合格但没有名字的Java类，也就是创建了一个没有名字的Java类的对象
  - **也就是说**：`没有名字的Java类 + 实现接口/继承类 + 重写方法 + 创建对象`表示创建了一个Swim接口的实现类对象，只不过这个类没有名字而已。
- 使用场景：如果实现类只要使用一次，就可以用匿名内部类简化代码。这是Lambda表达式的前置知识点

```java
interface Swim {
    void swim();
}
public class Test {
  public static void main(String[] args) {
    // 匿名内部类，直接创建接口的实现类对象
    Swim s = new Swim(){
      @Override
      public void swim() {
        System.out.println("1游泳");
      }
    };
    s.swim();
    goSwim(new Swim(){
      @Override
      public void swim() {
        System.out.println("2游泳");
      }
    });
  }
  static void goSwim(Swim s){
    s.swim();
  }
}

```

## 13.2 内部类的分类

| 类型    | 定义位置    | 创建方式                | 访问权限            |
| ----- | ------- | ------------------- | --------------- |
| 成员内部类 | 类内部，方法外 | `outer.new Inner()` | 可访问外部类所有成员      |
| 静态内部类 | 类内部，方法外 | `new Outer.Inner()` | 只能访问外部类静态成员     |
| 局部内部类 | 方法内部    | `new Inner()`       | 只能访问局部 final 变量 |
| 匿名内部类 | 方法内部    | `new 接口(){...}`     | 只能访问局部 final 变量 |

## 13.3 一句话总结

> **内部类就是"定义在另一个类内部的类"，它可以访问外部类的所有成员，用于代码封装和逻辑分组。**

***

# 14. API帮助文档 [API帮助文档](https://docs.oracle.com/en/java/javase/26/docs/api/index.html)

JDK自带的API帮助文档，可以在`搜JDK下载 -> 在线文档 -> 规模：API文档`查看即可

## 14.1 在什么情况下不导包

- 如果使用本包中的类
- 如果使用java.lang（核心）包下的类
  其他情况都需要导包

***

# 15. ArrayList（动态数组，Java里的数组是固定长度的，但是ArrayList是动态数组，长度可以变化）

> 特点：① 长度可变 ② 只能存储引用数据类型，不能存储基本数据类型，如果要存储基本数据类型，需要使用包装类

## 15.1 ArrayList的创建

- 创建方式：`ArrayList<类型> list = new ArrayList<>();`
- 例如：`ArrayList<String> list = new ArrayList<>();`泛型为String

## 15.2 常用方法

- `add(元素)`：在ArrayList的末尾添加一个元素
- `add(索引, 元素)`：在指定索引位置添加一个元素
- `remove(元素)`：删除指定元素
- `remove(索引)`：删除指定索引的元素
- `set(索引, 元素)`：将指定索引的元素替换为新的元素
- `get(索引)`：返回指定索引的元素
- `size()`：返回ArrayList的元素个数
- `isEmpty()`：判断ArrayList是否为空
- `clear()`：清空ArrayList，删除所有元素

## 15.3 基于基本数据类型对应的包装类（ 4 知识点有写）

- `int` -> `Integer`
- `long` -> `Long`
- `float` -> `Float`
- `double` -> `Double`
- `byte` -> `Byte`
- `short` -> `Short`
- `char` -> `Character`
- `boolean` -> `Boolean`

***

# 16. Java学习

> 针对哪些知识点需要学，可以按[黑马Java课程](https://www.bilibili.com/video/BV1TJxCzSEEZ/?p=134\&spm_id_from=333.1007.top_right_bar_window_history.content.click\&vd_source=918f6343da8b427dfd5b9a4b2fbab094)的顺序学习，它那里面的知识点基本都是常用的，其视频的每一章节就相当于一个知识点目录，你可以通过文档或者AI进行相关学习，如果还是不会，可以看视频。

## 16.1 知识点

- 基本语法：看菜鸟教程Java
- 面向对象：看菜鸟教程Java面向对象部分
- 常见API：主要学习字符串类String、集合类ArrayList、随机数类Random。主要看看[黑马Java课程](https://www.bilibili.com/video/BV1TJxCzSEEZ/?p=134\&spm_id_from=333.1007.top_right_bar_window_history.content.click\&vd_source=918f6343da8b427dfd5b9a4b2fbab094)、和菜鸟教程

## 16.2 如何通过文档进行学习

- 请教`豆包`和`deepseek`，我常用的提示词有：
  - ① “你是一个资深的Java工程师，请把`某某知识点`详细的解释一下，并给每一个知识点举一个例子，并给出一些面试题”
  - ② “你是一名资深的Java工程师，给我讲解一下`某某知识点`，并给出一些相关的面试八股文”
  - ③ 针对于看不懂的代码，可以这样子给AI说：“`看不懂的代码` + 解释一下每行的代码”

