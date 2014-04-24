Connected-Mind
==============

Artificial Life project implementing Artificial Intelligence and Evolutionary algorithms

## FlexArray data structure usage

```java

FlexArray<String> flexArray = new FlexArray<String>();
flexArray.add("value 1");
flexArray.add("value 2");

System.out.println(flexArray);
// output: [value 1, value 2]

flexArray.add(4, "value 4");
flexArray.add("value 5");
flexArray.add(6, "value 6");

System.out.println(flexArray);
// output: [value 1, value 2, null, null, value 4, value 5, value 6]

flexArray.add(4, "new 4", false);

System.out.println(flexArray);
// output: [value 1, value 2, null, null, value 4, value 5, value 6]

flexArray.add(4, "new 4", true);

System.out.println(flexArray);
// output: [value 1, value 2, null, null, new 4, value 5, value 6]

```