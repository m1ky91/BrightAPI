package it.micheledichio.brightapi.repository;

import it.micheledichio.brightapi.model.Realm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class RealmRepositoryIT {

    private Realm realmOne, realmTwo, realmThree;

    @Autowired
    private RealmRepository realmRepository;

    @BeforeAll
    public void initTests() {
        realmOne = new Realm(null, "testOne", "test", "test");
        realmTwo = new Realm(null, "testTwo", "test", "test");
        realmThree = new Realm(null, "testThree", "test", "test");
        realmOne = realmRepository.save(realmOne);
        realmTwo = realmRepository.save(realmTwo);
        realmThree = realmRepository.save(realmThree);
    }

    @Test
    public void findUserRealm() {
        List<Realm> all = realmRepository.findAll();
        assertThat(all.size()).isEqualTo(3);
        Optional<Realm> realm = realmRepository.findById(3L);
        assertTrue(realm.isPresent());
        assertThat(realm.get().getName()).isEqualTo(realmThree.getName());
    }

    @Test
    public void createUserRealm() {
        Realm realm = realmRepository.save(new Realm());
        assertThat(realm.getId()).isEqualTo(4);
        List<Realm> all = realmRepository.findAll();
        assertThat(all.size()).isEqualTo(4);
    }
}
