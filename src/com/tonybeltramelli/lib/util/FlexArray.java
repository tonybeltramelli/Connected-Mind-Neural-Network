package com.tonybeltramelli.lib.util;

import java.util.ArrayList;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 24/04/2014
 */
public class FlexArray<T> extends ArrayList<T>
{
    public FlexArray()
    {
        super();
    }

    @Override
    public void add(int index, T element)
    {
        add(index, element, true);
    }

    public void add(int index, T element, boolean override)
    {
        if(index > size())
        {
            for(int i = 0; i < index; i++)
            {
                if(i >= size()) super.add(i, null);
            }
        }

        if(override || index == size() || get(index) == null)
        {
            remove(index);
            super.add(index, element);
        }
    }

    @Override
    public T remove(int index)
    {
        if(index >= size()) return null;

        return super.remove(index);
    }
}
