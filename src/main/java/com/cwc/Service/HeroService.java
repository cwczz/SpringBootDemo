package com.cwc.Service;

import com.cwc.entity.Hero;

import java.util.List;

public interface HeroService {
    List<Hero> selectAll();
    int addhero(Hero hero);
    Hero OneHero(int id);
    int updatehero(Hero hero);

    void deletehero(Integer id);
}
