import day01.HistorianHysteriaSolution
import day02.RedNosedReportsSolution
import day03.MullItOverSolution
import day04.CeresSearchSolution
import day05.PrintQueueSolution
import day06.GuardGallivantSolution
import day07.BridgeRepairSolution
import day08.ResonantCollinearitySolution
import day09.DiskFragmenterSolution
import day10.HoofItSolution
import day11.PlutonianPebblesSolution
import day12.GardenGroups
import day13.ClawContraptionSolution
import day14.RestroomRedoubtSolution

object AdventOfCodeSolutionFactory {

    fun getSolution(day: Int) = when (day) {
        1 -> HistorianHysteriaSolution
        2 -> RedNosedReportsSolution
        3 -> MullItOverSolution
        4 -> CeresSearchSolution
        5 -> PrintQueueSolution
        6 -> GuardGallivantSolution
        7 -> BridgeRepairSolution
        8 -> ResonantCollinearitySolution
        9 -> DiskFragmenterSolution
        10 -> HoofItSolution
        11 -> PlutonianPebblesSolution
        12 -> GardenGroups
        13 -> ClawContraptionSolution
        14 -> RestroomRedoubtSolution

        else -> throw IllegalArgumentException("Day $day is not implemented")
    }

}