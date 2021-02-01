package com.test.helloworld.repository;

import com.test.helloworld.annotation.DummyProfile;
import org.springframework.stereotype.Repository;

@DummyProfile
@Repository
public class DummyItemRepository implements ItemRepository {

    @Override
    public long count() {
        return 123;
    }
}
