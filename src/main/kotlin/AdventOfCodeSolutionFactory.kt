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
import day15.WarehouseWoesSolution
import day16.ReindeerMazeSolution
import day17.ChronospatialComputerSolution
import day18.RamRunSolution

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
        15 -> WarehouseWoesSolution
        16 -> ReindeerMazeSolution
        17 -> ChronospatialComputerSolution
        18 -> RamRunSolution

        else -> throw IllegalArgumentException("Day $day is not implemented")
    }

}