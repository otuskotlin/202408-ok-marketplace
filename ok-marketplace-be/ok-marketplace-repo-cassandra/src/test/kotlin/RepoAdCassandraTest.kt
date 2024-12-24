package ru.otus.otuskotlin.marketplace.backend.repo.cassandra

import com.benasher44.uuid.uuid4
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.testcontainers.containers.CassandraContainer
import ru.otus.otuskotlin.marketplace.backend.repo.tests.*
import ru.otus.otuskotlin.marketplace.repo.common.AdRepoInitialized
import ru.otus.otuskotlin.marketplace.repo.common.IRepoAdInitializable
import java.time.Duration

@RunWith(Enclosed::class)
class CassandraTest {

    class RepoAdCassandraCreateTest : RepoAdCreateTest() {
        override val repo: IRepoAdInitializable = AdRepoInitialized(
            initObjects = initObjects,
            repo = repository("ks_create", uuidNew.asString())
        )
    }

    class RepoAdCassandraReadTest : RepoAdReadTest() {
        override val repo: IRepoAdInitializable = AdRepoInitialized(
            initObjects = initObjects,
            repo = repository("ks_read")
        )
    }

    class RepoAdCassandraUpdateTest : RepoAdUpdateTest() {
        override val repo: IRepoAdInitializable = AdRepoInitialized(
            initObjects = initObjects,
            repo = repository("ks_update", lockNew.asString())
        )
    }

    class RepoAdCassandraDeleteTest : RepoAdDeleteTest() {
        override val repo: IRepoAdInitializable = AdRepoInitialized(
            initObjects = initObjects,
            repo = repository("ks_delete")
        )
    }

    class RepoAdCassandraSearchTest : RepoAdSearchTest() {
        override val repo: IRepoAdInitializable = AdRepoInitialized(
            initObjects = initObjects,
            repo = repository("ks_search")
        )
    }

    @Ignore
    companion object {
        class TestCasandraContainer : CassandraContainer<TestCasandraContainer>("cassandra:3.11.2")

        private val container by lazy {
            TestCasandraContainer().withStartupTimeout(Duration.ofSeconds(300L))
        }

        fun repository(keyspace: String, uuid: String? = null): RepoAdCassandra {
            return RepoAdCassandra(
                keyspaceName = keyspace,
                host = container.host,
                port = container.getMappedPort(CassandraContainer.CQL_PORT),
                randomUuid = uuid?.let { { uuid } } ?: { uuid4().toString() },
            )
        }

        @JvmStatic
        @BeforeClass
        fun start() {
            container.start()
        }

        @JvmStatic
        @AfterClass
        fun finish() {
            container.stop()
        }
    }
}
