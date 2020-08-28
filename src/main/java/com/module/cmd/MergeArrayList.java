package com.module.cmd;

import java.util.ArrayList;
import java.util.Collection;

public class MergeArrayList extends ArrayList {
    @Override
    public boolean add(Object o) {
        int i = -1;
        if((i = indexOf(o)) > 0){
            Object o1 = get(i);
        }
        return super.add(o);
    }

    @Override
    public void add(int index, Object element) {
        //todo 合并，具有唯一标识的两个item需进行合并
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection c) {
        //todo 合并，具有唯一标识的两个item需进行合并
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        //todo 合并，具有唯一标识的两个item需进行合并
        return super.addAll(index, c);
    }
}
