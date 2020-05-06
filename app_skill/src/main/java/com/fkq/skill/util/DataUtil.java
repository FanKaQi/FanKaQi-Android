package com.fkq.skill.util;

import com.fkq.skill.constant.Constant;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public static List<String> getSkillString() {
        List<String> list = new ArrayList<>();
        list.add(Constant.SKILL_SWIPE_LIST);
        list.add(Constant.SKILL_SWIPE_GRID);
        return list;
    }
}
