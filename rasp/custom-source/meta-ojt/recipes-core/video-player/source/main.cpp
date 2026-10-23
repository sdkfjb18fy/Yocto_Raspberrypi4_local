#include "GStreamerBackend.hpp"
#include "PlayerCore.hpp"
 
#include <iostream>
#include <memory>
 
int main()
{
    PlayerCore player(std::make_unique<GStreamerBackend>());
 
    std::cout << "video-player started\n";
 
    /*
     * Production integration point.
     * Keep PlayerCore/GStreamer logic here for the real project.
     * USB Manager / Media Scanner / Playlist Manager / Qt / CAN
     * will be connected here later.
     *
     * Test-only UART/std::cin commands are intentionally kept in
     * test/player_test.cpp instead of production main.cpp.
     */
 
    return 0;
}