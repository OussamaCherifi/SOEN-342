package registry;

public interface IPairRegistry<K,V,T>{
    T createPair(K k, V v);

    boolean isPairMultiplicityValid(K k, V v);
}
