package io.duhle.pokemon.data.model


// fyi disini saya makek https://transform.tools/json-to-kotlin buat convert json respon ke data class
// gokil banyak bet
// ini tak pangkas beberapa biar gak kegedean

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @SerializedName("base_experience")
    val baseExperience: Int?,
    val types: List<TypeSlot>,
    val stats: List<StatSlot>,
    val abilities: List<AbilitySlot>,
    val sprites: Sprites
)

data class TypeSlot(val slot: Int, val type: NamedResource)

data class StatSlot(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: NamedResource
)

data class AbilitySlot(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val ability: NamedResource
)

data class NamedResource(
    val name: String,
    val url: String? = null)

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String?,
    val other: OtherSprites?
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?
)


/// Yang tak komen dibawah ini dari hasil convert https://transform.tools/json-to-kotlin
/// Terus tak ambil beberapa doang di atas

//data class PokemonDetail(
//    val id: Long,
//    val name: String,
//    @SerializedName("base_experience")
//    val baseExperience: Long,
//    val height: Long,
//    @SerializedName("is_default")
//    val isDefault: Boolean,
//    val order: Long,
//    val weight: Long,
//    val abilities: List<Ability>,
//    @SerializedName("past_abilities")
//    val pastAbilities: List<PastAbility>,
//    val forms: List<Form>,
//    @SerializedName("game_indices")
//    val gameIndices: List<Index>,
//    @SerializedName("held_items")
//    val heldItems: List<HeldItem>,
//    @SerializedName("location_area_encounters")
//    val locationAreaEncounters: String,
//    val moves: List<Mfe>,
//    val species: Species,
//    val sprites: Sprites,
//    val cries: Cries,
//    val stats: List<Stat>,
//    @SerializedName("past_stats")
//    val pastStats: List<PastStat>,
//    val types: List<Type>,
//    @SerializedName("past_types")
//    val pastTypes: List<Any?>,
//)
//
//data class Ability(
//    @SerializedName("is_hidden")
//    val isHidden: Boolean,
//    val slot: Long,
//    val ability: Ability2,
//)
//
//data class Ability2(
//    val name: String,
//    val url: String,
//)
//
//data class PastAbility(
//    val generation: Generation,
//    val abilities: List<Ability3>,
//)
//
//data class Generation(
//    val name: String,
//    val url: String,
//)
//
//data class Ability3(
//    @SerializedName("is_hidden")
//    val isHidden: Boolean,
//    val slot: Long,
//    val ability: Any?,
//)
//
//data class Form(
//    val name: String,
//    val url: String,
//)
//
//data class Index(
//    @SerializedName("game_index")
//    val gameIndex: Long,
//    val version: Version,
//)
//
//data class Version(
//    val name: String,
//    val url: String,
//)
//
//data class HeldItem(
//    val item: Item,
//    @SerializedName("version_details")
//    val versionDetails: List<VersionDetail>,
//)
//
//data class Item(
//    val name: String,
//    val url: String,
//)
//
//data class VersionDetail(
//    val rarity: Long,
//    val version: Version2,
//)
//
//data class Version2(
//    val name: String,
//    val url: String,
//)
//
//data class Mfe(
//    val move: Move,
//    @SerializedName("version_group_details")
//    val versionGroupDetails: List<VersionGroupDetail>,
//)
//
//data class Move(
//    val name: String,
//    val url: String,
//)
//
//data class VersionGroupDetail(
//    @SerializedName("level_learned_at")
//    val levelLearnedAt: Long,
//    @SerializedName("version_group")
//    val versionGroup: VersionGroup,
//    @SerializedName("move_learn_method")
//    val moveLearnMethod: MoveLearnMethod,
//    val order: Long?,
//)
//
//data class VersionGroup(
//    val name: String,
//    val url: String,
//)
//
//data class MoveLearnMethod(
//    val name: String,
//    val url: String,
//)
//
//data class Species(
//    val name: String,
//    val url: String,
//)
//
//data class Sprites(
//    val other: Other,
//    val versions: Versions2,
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class Other(
//    val home: Home,
//    val showdown: Showdown,
//    @SerializedName("dream_world")
//    val dreamWorld: DreamWorld,
//    @SerializedName("official-artwork")
//    val officialArtwork: OfficialArtwork,
//)
//
//data class Home(
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class Showdown(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class DreamWorld(
//    @SerializedName("front_female")
//    val frontFemale: Any?,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class OfficialArtwork(
//    val versions: Versions,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class Versions(
//    @SerializedName("generation-i")
//    val generationI: GenerationI,
//    @SerializedName("generation-ii")
//    val generationIi: GenerationIi,
//)
//
//data class GenerationI(
//    @SerializedName("red-and-blue")
//    val redAndBlue: RedAndBlue,
//    @SerializedName("red-and-green")
//    val redAndGreen: RedAndGreen,
//)
//
//data class RedAndBlue(
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class RedAndGreen(
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class GenerationIi(
//    @SerializedName("gold-and-silver")
//    val goldAndSilver: GoldAndSilver,
//)
//
//data class GoldAndSilver(
//    @SerializedName("front_default")
//    val frontDefault: Any?,
//)
//
//data class Versions2(
//    @SerializedName("generation-i")
//    val generationI: GenerationI2,
//    @SerializedName("generation-v")
//    val generationV: GenerationV,
//    @SerializedName("generation-ii")
//    val generationIi: GenerationIi2,
//    @SerializedName("generation-iv")
//    val generationIv: GenerationIv,
//    @SerializedName("generation-ix")
//    val generationIx: GenerationIx,
//    @SerializedName("generation-vi")
//    val generationVi: GenerationVi,
//    @SerializedName("generation-iii")
//    val generationIii: GenerationIii,
//    @SerializedName("generation-vii")
//    val generationVii: GenerationVii,
//    @SerializedName("generation-viii")
//    val generationViii: GenerationViii,
//)
//
//data class GenerationI2(
//    val yellow: Yellow,
//    @SerializedName("red-blue")
//    val redBlue: RedBlue,
//    @SerializedName("red-green-japan")
//    val redGreenJapan: RedGreenJapan,
//)
//
//data class Yellow(
//    @SerializedName("back_gbc")
//    val backGbc: String,
//    @SerializedName("back_gray")
//    val backGray: String,
//    @SerializedName("front_gbc")
//    val frontGbc: String,
//    @SerializedName("front_gray")
//    val frontGray: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_transparent")
//    val backTransparent: String,
//    @SerializedName("front_transparent")
//    val frontTransparent: String,
//    @SerializedName("back_transparent_gray")
//    val backTransparentGray: String,
//    @SerializedName("front_transparent_gray")
//    val frontTransparentGray: String,
//)
//
//data class RedBlue(
//    @SerializedName("back_gray")
//    val backGray: String,
//    @SerializedName("front_gray")
//    val frontGray: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_transparent")
//    val backTransparent: String,
//    @SerializedName("front_transparent")
//    val frontTransparent: String,
//    @SerializedName("back_transparent_gray")
//    val backTransparentGray: String,
//    @SerializedName("front_transparent_gray")
//    val frontTransparentGray: String,
//)
//
//data class RedGreenJapan(
//    @SerializedName("back_gray")
//    val backGray: String,
//    @SerializedName("front_gray")
//    val frontGray: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class GenerationV(
//    val icons: Icons,
//    @SerializedName("black-white")
//    val blackWhite: BlackWhite,
//)
//
//data class Icons(
//    val animated: Animated,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class Animated(
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class BlackWhite(
//    val animated: Animated2,
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class Animated2(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class GenerationIi2(
//    val gold: Gold,
//    val silver: Silver,
//    val crystal: Crystal,
//)
//
//data class Gold(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_transparent")
//    val backTransparent: String,
//    @SerializedName("front_transparent")
//    val frontTransparent: String,
//    @SerializedName("back_shiny_transparent")
//    val backShinyTransparent: String,
//    @SerializedName("front_shiny_transparent")
//    val frontShinyTransparent: String,
//)
//
//data class Silver(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_transparent")
//    val backTransparent: String,
//    @SerializedName("front_transparent")
//    val frontTransparent: String,
//    @SerializedName("back_shiny_transparent")
//    val backShinyTransparent: String,
//    @SerializedName("front_shiny_transparent")
//    val frontShinyTransparent: String,
//)
//
//data class Crystal(
//    val animated: Animated3,
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_transparent")
//    val backTransparent: String,
//    @SerializedName("front_transparent")
//    val frontTransparent: String,
//    @SerializedName("back_shiny_transparent")
//    val backShinyTransparent: String,
//    @SerializedName("front_shiny_transparent")
//    val frontShinyTransparent: String,
//)
//
//data class Animated3(
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class GenerationIv(
//    val icons: Icons2,
//    val platinum: Platinum,
//    @SerializedName("diamond-pearl")
//    val diamondPearl: DiamondPearl,
//    @SerializedName("heartgold-soulsilver")
//    val heartgoldSoulsilver: HeartgoldSoulsilver,
//)
//
//data class Icons2(
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class Platinum(
//    val animated: Animated4,
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class Animated4(
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class DiamondPearl(
//    val animated: Animated5,
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class Animated5(
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class HeartgoldSoulsilver(
//    val animated: Animated6,
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class Animated6(
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class GenerationIx(
//    val champions: Champions,
//    @SerializedName("scarlet-violet")
//    val scarletViolet: ScarletViolet,
//)
//
//data class Champions(
//    @SerializedName("front_shiny")
//    val frontShiny: Any?,
//    @SerializedName("front_default")
//    val frontDefault: Any?,
//)
//
//data class ScarletViolet(
//    @SerializedName("front_female")
//    val frontFemale: Any?,
//    @SerializedName("front_default")
//    val frontDefault: Any?,
//)
//
//data class GenerationVi(
//    @SerializedName("x-y")
//    val xY: XY,
//    val icons: Icons3,
//    @SerializedName("omegaruby-alphasapphire")
//    val omegarubyAlphasapphire: OmegarubyAlphasapphire,
//)
//
//data class XY(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class Icons3(
//    @SerializedName("front_female")
//    val frontFemale: Any?,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class OmegarubyAlphasapphire(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class GenerationIii(
//    val icons: Icons4,
//    val emerald: Emerald,
//    @SerializedName("ruby-sapphire")
//    val rubySapphire: RubySapphire,
//    @SerializedName("firered-leafgreen")
//    val fireredLeafgreen: FireredLeafgreen,
//)
//
//data class Icons4(
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class Emerald(
//    val animated: Animated7,
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class Animated7(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class RubySapphire(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class FireredLeafgreen(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class GenerationVii(
//    val icons: Icons5,
//    @SerializedName("ultra-sun-ultra-moon")
//    val ultraSunUltraMoon: UltraSunUltraMoon,
//    @SerializedName("lets-go-pikachu-lets-go-eevee")
//    val letsGoPikachuLetsGoEevee: LetsGoPikachuLetsGoEevee,
//)
//
//data class Icons5(
//    @SerializedName("front_female")
//    val frontFemale: Any?,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class UltraSunUltraMoon(
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class LetsGoPikachuLetsGoEevee(
//    val icons: Icons6,
//    @SerializedName("back_shiny")
//    val backShiny: String,
//    @SerializedName("back_female")
//    val backFemale: String,
//    @SerializedName("front_shiny")
//    val frontShiny: String,
//    @SerializedName("back_default")
//    val backDefault: String,
//    @SerializedName("front_female")
//    val frontFemale: String,
//    @SerializedName("front_default")
//    val frontDefault: String,
//    @SerializedName("back_shiny_female")
//    val backShinyFemale: String,
//    @SerializedName("front_shiny_female")
//    val frontShinyFemale: String,
//)
//
//data class Icons6(
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class GenerationViii(
//    val icons: Icons7,
//    @SerializedName("brilliant-diamond-shining-pearl")
//    val brilliantDiamondShiningPearl: BrilliantDiamondShiningPearl,
//)
//
//data class Icons7(
//    @SerializedName("front_female")
//    val frontFemale: Any?,
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class BrilliantDiamondShiningPearl(
//    @SerializedName("front_default")
//    val frontDefault: String,
//)
//
//data class Cries(
//    val latest: String,
//    val legacy: String,
//)
//
//data class Stat(
//    @SerializedName("base_stat")
//    val baseStat: Long,
//    val effort: Long,
//    val stat: Stat2,
//)
//
//data class Stat2(
//    val name: String,
//    val url: String,
//)
//
//data class PastStat(
//    val generation: Generation2,
//    val stats: List<Stat3>,
//)
//
//data class Generation2(
//    val name: String,
//    val url: String,
//)
//
//data class Stat3(
//    @SerializedName("base_stat")
//    val baseStat: Long,
//    val effort: Long,
//    val stat: Stat4,
//)
//
//data class Stat4(
//    val name: String,
//    val url: String,
//)
//
//data class Type(
//    val slot: Long,
//    val type: Type2,
//)
//
//data class Type2(
//    val name: String,
//    val url: String,
//)
//
