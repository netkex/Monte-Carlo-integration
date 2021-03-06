# Тестовое задание для проекта "KMath development" [JBR_NOZIK]

В данном репозитории приведена реализация интегрирования 
методом Монте—Карло на языке Kotlin. 

Также данный проект включает в себя реализацию алгоритма
Метрополиса—Гастингса для генерации точек на плоскости 
по заданному распределению.

## Реализация для двумерного случая 

Реализация только для двумерного случая представлена в ветке `2D_version`.

## Описание реализации

#### class Point
Для описания точки в n-мерном пространстве используются класс `PointND`, 
который является наследником класса `Point`. У этого класса должен быть реализован
пустой конструктор, генерирующий точку в 0, а также функция `generateNewPoint`, 
которая возвращает случайно сгенерированную точку, отцентрированную относительно текущей.

#### Функция интегрирования
```kotlin 
fun integrate(
    f: (pnt: T) -> Double,
    p: (pnt: T) -> Double,
    firstPoint: T? = null,
    N: Int = intN): Double
```
Функция принимает в качестве параметров функцию, которую нужно 
интегрировать (`f`), функцию распределения (`p`), 
а также два опциональных параметра:
* `firstPoint` - первая точка в последовательности случайных точек, 
которые генерирует алгоритм Метрополиса—Гастингса  
* `N` - количество генерируемых точек для вычисления интеграла

Эта функция работает для всех типов `T`, которые являются представителями класса
`Point`.

#### Функция генерации точек по распределению
```kotlin 
fun generatePoints(
    startPoint: T, 
    p: (pnt: T) -> Double): Sequence<T>
```
Функция возвращает бесконечную последовательность точек, которые 
удовлетворяют закону распределения (`p`), начиная с точки `startPoint`.

Эта функция работает для всех типов `T`, которые являются представителями класса
`Point`.

### Тесты 

Реализованы unit-тесты для 1мерного, 2мерного и 3мерного случаев. 