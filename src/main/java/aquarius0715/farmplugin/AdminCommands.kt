package aquarius0715.farmplugin

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.lang.NumberFormatException

class AdminCommands(plugin: FarmPlugin): CommandExecutor {

    private val prefix = plugin.prefix
    private val seeds = plugin.seeds

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player || !sender.hasPermission("admin")) {

            sender.sendMessage("$prefix このコマンドは管理者権限を所有しているプレイヤーのみ実行できます。")

            return false

        }

        when (label) {

            "farmadmin" -> {

                when (args.size) {

                    0 -> {

                        sender.sendMessage("$prefix FarmCore Ver 0.0.1")
                        sender.sendMessage("$prefix Created by Aquarius0715")

                    }

                    1 -> {

                        when (args[0]) {

                            "seedlist" -> {

                                sender.sendMessage("$prefix 種子一覧 ページ 1 / ${(seeds.keys.size % 10) + 1}")

                                for ((count, seed) in seeds.keys.withIndex()) {

                                    if (count > 9) break

                                    sender.sendMessage("$prefix 種子ID: $count 種子識別名: $seed")

                                }

                                return true

                            }

                        }

                    }

                    2 -> {

                        when (args[0]) {

                            "seedlist" -> {

                                val page = (seeds.keys.size % 10) + 1

                                 try {

                                     args[1].toInt()

                                 } catch (e: NumberFormatException) {

                                     sender.sendMessage("$prefix ページ番号には数字を入れてください。")

                                     return false

                                 }

                                if (args[1].toInt() > page) {

                                    sender.sendMessage("$prefix ページ番号が大きすぎます。")

                                    return false

                                }

                                sender.sendMessage("$prefix 種子一覧 ページ ${args[1].toInt()} / ${(seeds.keys.size % 10) + 1}")

                                for ((count, seed) in seeds.keys.withIndex()) {

                                    if (count < seeds.keys.size * 10 - 10) continue

                                    if (count > seeds.keys.size * 10 - 1) break

                                    sender.sendMessage("$prefix 種子ID: $count 種子識別名: $seed")

                                }

                            }

                            "getseed" -> {

                                if (!seeds.containsKey(args[1])) {

                                    sender.sendMessage("$prefix そのような名前の種子は登録されていません。")

                                    return false

                                } else {

                                    sender.inventory.addItem(seeds[args[1]])

                                }

                            }

                        }

                    }

                }

            }

        }

        return false

    }

}