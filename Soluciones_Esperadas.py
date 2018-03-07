def soluciones_esperadas(T, a):
    cont = 0
    while(T>0.01):
        print(T)
        cont+=1
        T *= a
    return cont

def soluciones_deseadas(T, cantidad_deseada):
    cont = 0
    a = 0.99
    while cont!=cantidad_deseada:
        temp = T
        cont = 0
        while(temp>0.01):
            cont+=1
            temp *= a
        print(cont, a)
        a = a - 0.01
    return a + 0.01
