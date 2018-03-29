package org.quark.microapidemo.contract;

public interface IMapRequestFifth<T1, T2, T3, T4, T5> {
    /* 将用户请求传输对象映射为领域对象 */
    T1 mapToDomainFirst();
    /* 将用户请求传输对象映射为领域对象 */
    T2 mapToDomainSecond();
    /* 将用户请求传输对象映射为领域对象 */
    T3 mapToDomainThird();
    /* 将用户请求传输对象映射为领域对象 */
    T4 mapToDomainFourth();
    /* 将用户请求传输对象映射为领域对象 */
    T5 mapToDomainFifth();
}
