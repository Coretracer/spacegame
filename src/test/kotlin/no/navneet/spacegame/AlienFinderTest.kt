package no.navneet.spacegame

import no.navneet.spacegame.bin.Alien
import no.navneet.spacegame.bin.Space
import no.navneet.spacegame.match.AlienFinder
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AlienFinderTest {

    private val alienString1 =
        """
            --o-----o--
            ---o---o---
            --ooooooo--
            -oo-ooo-oo-
            ooooooooooo
            o-ooooooo-o
            o-o-----o-o
            ---oo-oo---
               """.trimIndent()
    val alienString2 =
        """
            ---oo---
            --oooo--
            -oooooo-
            oo-oo-oo
            oooooooo
            --o--o--
            -o-oo-o-
            o-o--o-o
        """.trimIndent()

    private val spaceString =
        """
            ----o--oo----o--ooo--ooo--o------o---oo-o----oo---o--o---------o----o------o-------------o--o--o--o-
            --o-o-----oooooooo-oooooo---o---o----o------ooo-o---o--o----o------o--o---ooo-----o--oo-o------o----
            --o--------oo-ooo-oo-oo-oo-----O------------ooooo-----oo----o------o---o--o--o-o-o------o----o-o-o--
            -------o--oooooo--o-oo-o--o-o-----oo--o-o-oo--o-oo-oo-o--------o-----o------o-ooooo---o--o--o-------
            ------o---o-ooo-ooo----o-----oo-------o---oo-ooooo-o------o----o--------o-oo--ooo-oo-------------o-o
            -o--o-----o-o---o-ooooo-o-------oo---o---------o-----o-oo-----------oo----ooooooo-ooo-oo------------
            o-------------ooooo-o--o--o--o-------o--o-oo-oo-o-o-o----oo------------o--oooo--ooo-o----o-----o--o-
            --o-------------------------oo---------oo-o-o--ooo----oo----o--o--o----o--o-o-----o-o------o-o------
            -------------------o----------o------o--o------o--------o--------o--oo-o-----oo-oo---o--o---o-----oo
            ----------o----------o---o--------------o--o----o--o-o------------oo------o--o-o---o-----o----------
            ------o----o-o---o-----o-o---o-----oo-o--------o---------------------------------o-o-o--o-----------
            ---------------o-------o-----o-------o-------------------o-----o---------o-o-------------o-------oo-
            -o--o-------------o-o-----o--o--o--oo-------------o----ooo----o-------------o----------oo----o---o-o
            -o--o-------------o----oo------o--o-------o--o-----o-----o----o-----o--o----o--oo-----------o-------
            -o-----oo-------o------o----o----------o--o----o-----o-----o-------o-----------o---o-o--oooooo-----o
            -o--------o-----o-----o---------oo----oo---o-o---------o---o--oooo-oo--o-------o------oo--oo--o-----
            ------------o---------o---------o----oooo-------------oo-oo-----ooo-oo-----o-------o-oo-oooooooo---o
            ----------------------o------------oooooooo---o-----o-------o--oooooo-o------------o-o-ooooooo-o----
            ------------o------o---o---o-------oo-oo--o--o---------o--o-o-o-ooooo-o--------------oo-o----o-oo-o-
            ---o-o----------oo-------oo----o----oooooooo-------o----o-o-o-o-----o-o-----o----------ooo-oo--o---o
            -o-o---------o-o---------------o--o--o--ooo---ooo-------o------oo-oo------------o--------o--o-o--o--
            -------oo---------------------------o-oo----------o------o-o-------o-----o----o-----o-oo-o-----o---o
            ---o--------o-----o-------o-oo-----oo--oo-o----oo----------o--o---oo------oo----o-----o-------o-----
            ---o--ooo-o---------o-o----o------------o---------o----o--o-------o----o--------o----------------oo-
            ---o------o----------------o----o------o------o---oo-----------o-------------o----------oo---------o
            --oo---------------o--o------o---o-----o--o-------------o------o-------o-----o-----o----o------o--o-
            -o-------o----------o-o-o-------o-----o--o-o-----------o-oo-----------o------o---------o-----o-o----
            ----------o----o-------o----o--o------o------------o---o---------------oo----o-----ooo--------------
            ----o--------oo----o-o----o--o------ooo----o-oooo---o--o-oo--------o-oo-----o-o---o-o--o-----oo-----
            ------o--------o-ooooo----o---o--o-----o---------------o-o-------o-----o----------------------------
            o-------oo----o--oooooo-o---o--o------oooo----------o-oo-------o---o----------o------oo-------------
            -o---o----------o--oo-oo-o---o-----o-o-----------------------oo--o------o------o--------------------
            -----oo-o-o-o---ooooooooo----o----o--------o--o---oo---o------------o----------o-o---o------o-o--oo-
            ------o------o---ooo-o---------------------------o--o---o---o----o--o-------o-----o------o----o----o
            -------o----------ooo-o-----o----o---o--o-oo--o--o-o--o------o--o-oo---ooo------------------------o-
            -o-------o------o-o--ooo--o---o---oo-----o----o-------------o----o-ooo-o------o--o-o------o-o-------
            ---oo--o---o-o---------o---o--------------o--o-----o-------o-----o--o---o-oo--------o----o----o-----
            o------o----oo-o-----------oo--o---o--------o-o------o-------o-o------o-oo---------o-----oo---------
            ----o--o---o-o-----------o---o------------o-------o----o--o--o--o-o---------------o-----------------
            -------oo--o-o-----o-----o----o-o--o----------------------o-------o------o----oo----ooo---------o---
            o-----oo-------------------o--o-----o-----------o------o-------o----o-----------o----------------o--
            --o---o-------o------------o--------------------o----o--o-------------oo---o---------oo--------o----
            --o--------o---------o------------o------o-------o------------o-------o---o---------ooooo-----------
            ------o--------------o-o-o---------o---o-------o--o-----o-------o-o----------o-----oo-ooo----------o
            --o---------------o----o--oo-------------o---------o-------------------oo---------oo-o-ooo----------
            -o-----------o------ooo----o----------------ooo-----o--------o--o---o-----------o-o-oooooo--------oo
            -o---o-------o---o-oooo-----o-------------------o----oo-----------------o--o--------o--o------o--o--
            -------o---o------oooooo--o----ooo--o--------o-------o----------------------------oo-oo-o--o--------
            o--oo------o-----oo--o-oo------------oo--o------o--o-------------oo----o------------oooo-o------oo--
            -----o----------ooooooooo--------------oo--------------oo-----o-----o-o--o------o----------o----o---
            
        """.trimIndent()

    private val spaceString2 =
        """
            ----o--oo----o--ooo--ooo--o------o---oo-o----oo---o--o---------o----o------o-------------o--o--o--o-
            --o-o-----oooooooo-oooooo---o---o----o------ooo-o---o--o----o------o--o---ooo-----o--oo-o------o----
            --o--------oo-ooo-oo-oo-oo-----O------------ooooo-----oo----o------o---o--o--o-o-o------o----o-o-o--
            -------o----o-----o--o-o--o-o-----oo--o-o-oo--o-oo-oo-o--------o-----o------o-ooooo---o--o--o-------
            ------o------o---o-----o-----oo-------o---oo-ooooo-o------o----o--------o-oo--ooo-oo-------------o-o
            -o--o-------ooooooo--oo-o-------oo---o---------o-----o-oo-----------oo----ooooooo-ooo-oo------------
            o----------oo-ooo-oo---o--o--o-------o--o-oo-oo-o-o-o---o-o------------o--oooo--ooo-o----o-----o--o-
            --o-------ooooooooooo-------oo---------oo-o-o--ooo-----oooo----o--o----o--o-o-----o-o------o-o------
            ----------o-ooooooo-o---------o------o--o------o------oooooo-----o--oo-o-----oo-oo---o--o---o-----oo
            ----------o-o-----o-oo---o--------------o--o----o--o-oo-oo-oo-----oo------o--o-o---o-----o----------
            ------o------oo-oo-----o-o---o-----oo-o--------o-----oooooooo--------------------o-o-o--o-----------
            ---------------o-------o-----o-------o------------------oo-----o---------o-o-------------o-------oo-
            -o--o-------------o-o-----o--o--o--oo-------------o---o-oo-o--o-------------o----------oo----o---o-o
            -o--o-------------o----oo------o--o-------o--o-----o-o-o--o-o-o-----o--o----o--oo-----------o-------
            -o-----oo-------o------o---o-----o-----o--o----o-----o-----o-------o-----------o---o-o--oooooo-----o
            -o--------o-----o-----o-----o---o-----oo---o-o---------o---o--oooo-oo--o-------o------oo--oo--o-----
            ------------o---------o----ooooooo---oooo-------------oo-oo-----ooo-oo-----o-------o-oo-oooooooo---o
            ----------------------o---oo-oo-ooo-ooooooo---o-----o-------o--oooooo-o------------o-o-ooooooo-o----
            ------------o------o---o-oooooooooooo-oo--o--o---------o--o-o-o-ooooo-o--------------oo-o----o-oo-o-
            ---o-o----------oo-------o-ooooooo-ooooooooo-------o----o-o-o-o-----o-o-----o----------ooo-oo--o---o
            -o-o---------o-o---------o-o-----o-o-o--ooo---ooo-------o------oo-oo------------o--------o--o-o--o--
            -------oo-------------------oo-oo---o-oo----------o------o-o-------o-----o----o-----o-oo-o-----o---o
            ---o--------o-----o-------o-oo-----oo--oo-o----oo----------o--o---oo------oo----o-----o-------o-----
            ---o--ooo-o---------o-o----o------------o---------o----o--o-------o----o--------o----------------oo-
            ---o------o----------------o----o------o------o---oo-----------o-------------o----------oo---------o
            --oo---------------o--o------o---o-----o--o-------------o------o---------oo--------o----o------o--o-
            -o-------o----------o-o-o-------o-----o--o-o-----------o-oo-------------oooo-----------o-----o-o----
            ----------o----o-------o----o--o------o------------o---o---------------oooooo------ooo--------------
            ----o--------oo----o-o----o--o------ooo----o-oooo---o--o-oo--------o-ooo-oo-ooo---o-o--o-----oo-----
            ------o--------o-ooooo----o---o--o-----o---------------o-o-------o----oooooooo----------------------
            o-------oo----o--oooooo-o---o--o------oooo----------o-oo-------o---o----o--o--o------oo-------------
            -o---o----------o--oo-oo-o---o-----o-o-----------------------oo--o-----o-oo-o--o--------------------
            -----oo-o-o-o---ooooooooo----o----o--------o--o---oo---o------------o-o-o--o-o-o-o---o------o-o--oo-
            ------o------o---ooo-o--------------------oo-----o--o---o---o----o--o-------o-----o------o----o----o
            -------o----------ooo-o-----o----o---o---oooo----o-o--o------o--o-oo---ooo------------------------o-
            -o-------o------o-o--ooo--o---o---oo----oooooo--------------o----o-ooo-o------o--o-o------o-o-------
            ---oo--o---o-o---------o---o-----------oo-oo-oo----o-------o-----o--o---o-oo--------o----o----o-----
            o------o----oo-o-----------oo--o---o---oooooooo------o-------o-o------o-oo---------o-----oo---------
            ----o--o---o-o-----------o---o-----------o--o-----o----o--o--o--o-o---------------o-----------------
            -------oo--o-o-----o-----o----o-o--o----o-oo-o------------o-------o------o----oo----ooo---------o---
            o-----oo-------------------o--o-----o--o-o--o-o-o------o-------o----o-----------o----------------o--
            --o---o-------o------------o--------------------o----o--o-------------oo---o---------oo--------o----
            --o--------o---------o------------o------o-------o------------o-------o---o---------ooooo-----------
            ------o--------------o-o-o---------o---o-------o--o-----o-------o-o----------o-----oo-ooo----------o
            --o---------------o----o--oo-------------o---------o-------------------oo---------oo-o-ooo----------
            -o-----------o------ooo----o----------------ooo-----o--------o--o---o-----------o-o-oooooo--------oo
            -o---o-------o---o-oooo-----o-------------------o----oo-----------------o--o--------o--o------o--o--
            -------o---o------oooooo--o----ooo--o--------o-------o----------------------------oo-oo-o--o--------
            o--oo------o-----oo--o-oo------------oo--o------o--o-------------oo----o------------oooo-o------oo--
            -----o----------ooooooooo--------------oo--------------oo-----o-----o-o--o------o----------o----o---
        """.trimIndent()


    private val alienString3 =
        """
          --o-----o-- 
        """.trimIndent().replace("\\s".toRegex(), "")

    @Test
    fun findAlienBasicTest() {
        val alien1 = Alien(alienString1.split("\n"))
        val space = Space(spaceString2.split("\n"))
        assertThat(AlienFinder.findAlien(space, alien1)).isNotEmpty
    }
//AlienFinder.extractFrame(AlienFinder.Coordinate(10,3), AlienFinder.Size(alien1.pixelRows[0].length, alien1.pixelRows.size), space)
    //70,25
    //39,33
    @Test
    fun extractFrameTest() {
        val space = Space(alienString1.split("\n"))
        val frame= AlienFinder.extractFrame( AlienFinder.Coordinate(2,8), AlienFinder.Size(1,1),space)
        assertThat(frame).isNotEmpty
    }

    @Test
    fun matchFrameToAlienTest() {
        val space = Space(alienString1.split("\n"))
        val frame= AlienFinder.extractFrame( AlienFinder.Coordinate(2,8), AlienFinder.Size(1,1),space)
        val matched = frame?.let { AlienFinder.matchAlienInFrame(it,Alien(space.pixelRows) )}
        assertThat(matched).isTrue

    }

}