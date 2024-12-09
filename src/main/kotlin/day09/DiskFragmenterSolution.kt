package day09

import AdventOfCodeSolution
import kotlin.math.min


object DiskFragmenterSolution : AdventOfCodeSolution<Long> {

    override val day: Int = 9
    override val problemName: String = "Disk Fragmentation"

    override fun part1(input: String): Long = packDisk(parseInput(input).toMutableList()).computeChecksum()

    override fun part2(input: String): Long = defragmentDisk(parseInput(input).toMutableList()).computeChecksum()

    sealed class FileSystemBlock(val size: Int) {
        class File(val id: Long, size: Int) : FileSystemBlock(size)
        class Free(size: Int) : FileSystemBlock(size)
    }

    fun parseInput(input: String): List<FileSystemBlock> = input
        .mapIndexed { idx, blockWidth ->
            val blockWidth = blockWidth.digitToInt()
            when {
                blockWidth == 0 -> null
                idx % 2 == 0 -> FileSystemBlock.File(((idx / 2).toLong()), blockWidth)
                else -> FileSystemBlock.Free(blockWidth)
            }
        }
        .filterNotNull()

    fun packDisk(disk: MutableList<FileSystemBlock>): MutableList<FileSystemBlock> {
        var fileBlockIdx = disk.indexOfLast { block -> block is FileSystemBlock.File }
        var freeBlockIdx = disk.indexOfFirst { block -> block is FileSystemBlock.Free }
        while (freeBlockIdx < fileBlockIdx) {
            val fileBlock = disk[fileBlockIdx] as FileSystemBlock.File
            val freeBlock = disk[freeBlockIdx]

            // remove previously occupied file blocks
            disk.removeAt(fileBlockIdx)
            disk.addAll(fileBlockIdx, buildList {
                if (freeBlock.size < fileBlock.size) add(
                    FileSystemBlock.File(
                        fileBlock.id,
                        fileBlock.size - freeBlock.size
                    )
                )
                FileSystemBlock.Free(min(freeBlock.size, fileBlock.size))
            })
            // insert file blocks at correct position
            disk.removeAt(freeBlockIdx)
            disk.addAll(freeBlockIdx, buildList {
                add(FileSystemBlock.File(fileBlock.id, min(fileBlock.size, freeBlock.size)))
                if (freeBlock.size > fileBlock.size) {
                    add(FileSystemBlock.Free(freeBlock.size - fileBlock.size))
                }
            })

            fileBlockIdx = disk.indexOfLast { block -> block is FileSystemBlock.File }
            freeBlockIdx = disk.indexOfFirst { block -> block is FileSystemBlock.Free }
        }

        return disk
    }

    fun defragmentDisk(disk: MutableList<FileSystemBlock>): List<FileSystemBlock> = disk
        .filter { it is FileSystemBlock.File }
        .asReversed()
        .forEach { blockToMove ->
            val fileBlock = blockToMove as FileSystemBlock.File
            val fileBlockIdx = disk.lastIndexOf(fileBlock)

            val freeBlockIdx = disk.indexOfFirst { it is FileSystemBlock.Free && it.size >= fileBlock.size }
            if (freeBlockIdx != -1 && freeBlockIdx < fileBlockIdx) {
                val freeBlock = disk[freeBlockIdx]

                // free up space where the file previously was
                disk[fileBlockIdx] = FileSystemBlock.Free(fileBlock.size)
                // insert the file at the previously free place (also insert all left over free blocks)
                disk.removeAt(freeBlockIdx)
                disk.addAll(freeBlockIdx, buildList {
                    add(FileSystemBlock.File(fileBlock.id, fileBlock.size))
                    if (fileBlock.size != freeBlock.size) {
                        add(FileSystemBlock.Free(freeBlock.size - fileBlock.size))
                    }
                })
            }
        }
        .let {
            disk
        }

    fun List<FileSystemBlock>.computeChecksum(): Long =
        this
            .fold(0 to 0L) { (diskIdx, sum), block ->
                (diskIdx + block.size) to (sum + when (block) {
                    is FileSystemBlock.Free -> 0
                    is FileSystemBlock.File -> (diskIdx until diskIdx + block.size).sumOf { idx -> idx * block.id }
                })
            }
            .second
}