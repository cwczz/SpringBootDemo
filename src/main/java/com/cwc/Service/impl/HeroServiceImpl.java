package com.cwc.Service.impl;

import com.cwc.Mapper.HeroMapper;
import com.cwc.Service.HeroService;
import com.cwc.entity.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HeroServiceImpl implements HeroService {
    @Autowired
    private HeroMapper heroMapper;
    @Override
    public List<Hero> selectAll() {
        return heroMapper.selectAll();
    }

    @Override
    public int addhero(Hero hero) {
        return heroMapper.insert(hero);
    }

    @Override
    public Hero OneHero(int id) {
        return heroMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatehero(Hero hero) {
        return heroMapper.updateByPrimaryKey(hero);
    }

    @Override
    public void deletehero(Integer id) {
        heroMapper.deleteByPrimaryKey(id);
    }
}
