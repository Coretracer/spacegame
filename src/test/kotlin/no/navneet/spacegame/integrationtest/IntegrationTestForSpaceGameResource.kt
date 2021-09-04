package no.navneet.spacegame.integrationtest


class IntegrationTestForSpaceGameResource {
    private val testApp = TestApp
/*
    @Test
    fun `test bulk upload via resource` () {
        val numberOfVisning = 20
        val payload = createPayload(numberOfVisning)

        val result = testApp.httpClient
            .target("http://localhost:${testApp.appPort}/visnings")



            .request()
            .post(Entity.json(payload))


        assertThat(result.status).isEqualTo(200)
        val visningEntities = result.readEntity(object : GenericType<List<VisningEntity>>() {})
        assertThat(visningEntities.size).isEqualTo(numberOfVisning)
        visningEntities.forEach { visningEntity ->
            visningEntity.validate()
        }
    }

    @Test
    fun `test fetch uploaded via resource` () {
        val storedVisningEntity = fixtures.insertDataViaBulkUpdate()[0]

        val result = testApp.httpClient
            .target("http://localhost:${testApp.appPort}/visnings?seriesId=${storedVisningEntity.seriesId}")
            .request()
            .get()
            .readEntity(object : GenericType<List<VisningEntity>>() {})

        assertThat(result).isNotEmpty
        assertThat(result.map { it.seriesId }.contains(storedVisningEntity.seriesId))
    } */
}
