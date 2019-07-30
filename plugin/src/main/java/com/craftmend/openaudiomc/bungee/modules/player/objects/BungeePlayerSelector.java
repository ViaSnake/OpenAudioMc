package com.craftmend.openaudiomc.bungee.modules.player.objects;

import com.craftmend.openaudiomc.OpenAudioMcCore;
import com.craftmend.openaudiomc.spigot.OpenAudioMcSpigot;
import com.craftmend.openaudiomc.spigot.modules.regions.RegionModule;
import lombok.AllArgsConstructor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BungeePlayerSelector {

    private String selector;

    /**
     * this turns selectors like @a[r=5] into a usable list, since
     * 1.13 spigot removed this feature, FOR SOME REASON.. thanks guys..
     *
     * @param commandSender the sender
     * @return players following the selector
     */
    public List<ProxiedPlayer> getPlayers(CommandSender commandSender) {
        List<ProxiedPlayer> players = new ArrayList<>();

        if (selector.startsWith("@a")) {
            //everyone
            if (getArgument("server").length() != 0) {
                String targetServer = getArgument("server");
                for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                    if (player.getServer().getInfo().getName().equals(serverName(commandSender))) {
                        players.add(player);
                    }
                }
            }
            else if (getArgument("global").length() != 0) {
                String targetServer = getArgument("global");
                if (targetServer.equals("true")) {
                    players.addAll(ProxyServer.getInstance().getPlayers());
                }
            }
            else {
                String serverName = serverName(commandSender);
                if (serverName == null) {
                    commandSender.sendMessage(OpenAudioMcCore.getLOG_PREFIX() + "Only players can play sounds for their entire server. If you want a sound that can be heard across all servers, please use @a[global=true]");
                    return new ArrayList<>();
                }
            }
        } else if (selector.length() <= 16) {
            //player
            ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(selector);
            if (proxiedPlayer != null) players.add(proxiedPlayer);
        } else {
            //you fucked it
            commandSender.sendMessage(OpenAudioMcCore.getLOG_PREFIX() + "Invalid player query. Try something like @a, @a[server=lobby], username or other arguments.");
        }
        return players;
    }

    private String serverName(CommandSender sender) {
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (sender instanceof ProxiedPlayer) {
            return player.getServer().getInfo().getName();
        }
        return null;
    }

    private String getArgument(String key) {
        StringBuilder result = new StringBuilder();
        String[] arguments = selector.split(key + "=");
        if (arguments.length == 1) return "";
        for (byte type : arguments[1].getBytes()) {
            char element = (char) type;
            if (element == ',' || element == ']') {
                return result.toString();
            } else {
                result.append(element);
            }
        }
        return result.toString();
    }

}