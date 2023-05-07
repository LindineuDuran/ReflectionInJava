package br.com.llduran.reflectioninjava.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//http://blog.gabrielamorim.com/java-reflection-um-exemplo-pratico/
public class ReflectionMapper
{
    /**
     * @param obj
     * @return Map containing the attributes' names and it's values
     */
    public static Map<String, Object> getAttributesMap(Object obj)
    {
        Map<String, Object> attributesMap = new HashMap<String, Object>();

        Class<?> objClass = obj.getClass();
        Method[] methods = objClass.getMethods();

        for(Method method : methods)
        {
            if (isGetter(method))
            {
                String attributeName = getAttributeName(method.getName());
                try
                {
                    Object value = method.invoke(obj);
                    attributesMap.put(attributeName, value);
                }
                catch (Exception e)
                {
                    throw new RuntimeException("Problema ao gerar o mapa", e);
                }
            }
        }

        return attributesMap;
    }

    private static boolean isGetter(Method m)
    {
        return m.getName().startsWith("get") &&
                m.getReturnType() != void.class &&
                m.getParameterTypes().length == 0;
    }

    private static String getAttributeName(String nomeGetter)
    {
        StringBuffer retorno = new StringBuffer();
        retorno.append(nomeGetter.substring(3, 4).toLowerCase());
        retorno.append(nomeGetter.substring(4));
        return retorno.toString();
    }
}
