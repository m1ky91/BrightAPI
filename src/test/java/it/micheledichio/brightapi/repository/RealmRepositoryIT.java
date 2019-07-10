package it.micheledichio.brightapi.repository;

import it.micheledichio.brightapi.model.Realm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("Find all realm entities and check size then find by id and check the entity returned")
    public void findUserRealm() {
        List<Realm> all = realmRepository.findAll();
        assertThat(all.size()).isEqualTo(3);
        Optional<Realm> realm = realmRepository.findById(3L);
        assertTrue(realm.isPresent());
        assertThat(realm.get().getName()).isEqualTo(realmThree.getName());
    }

    @Test
    @DisplayName("Find a user realm by name and check the entity returned")
    public void findUserRealmByName() {
        Optional<Realm> realm = realmRepository.findByName("testThree");
        assertTrue(realm.isPresent());
        assertThat(realm.get().getName()).isEqualTo(realmThree.getName());
    }

    @Test
    @DisplayName("Save a realm entity and check the new size")
    public void createUserRealm() {
        Realm realm = realmRepository.save(new Realm());
        assertThat(realm.getId()).isEqualTo(4);
        List<Realm> all = realmRepository.findAll();
        assertThat(all.size()).isEqualTo(4);
    }
}
