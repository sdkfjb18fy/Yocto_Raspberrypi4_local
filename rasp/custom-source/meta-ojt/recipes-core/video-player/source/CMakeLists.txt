cmake_minimum_required(VERSION 3.10)
project(video-player VERSION 1.2 LANGUAGES CXX)
 
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
 
option(BUILD_PLAYER_TEST "Build separate UART/CLI test executable" ON)
 
find_package(PkgConfig REQUIRED)
find_package(Threads REQUIRED)
pkg_check_modules(GSTREAMER REQUIRED gstreamer-1.0)
 
set(PLAYER_COMMON_SOURCES
    PlayerCore.cpp
    GStreamerBackend.cpp
)
 
# Production application keeps the existing flat source layout.
add_executable(video-player
    main.cpp
    ${PLAYER_COMMON_SOURCES}
)
 
target_include_directories(video-player PRIVATE
    ${CMAKE_CURRENT_SOURCE_DIR}
    ${GSTREAMER_INCLUDE_DIRS}
)
 
target_compile_options(video-player PRIVATE ${GSTREAMER_CFLAGS_OTHER})
target_link_directories(video-player PRIVATE ${GSTREAMER_LIBRARY_DIRS})
target_link_libraries(video-player PRIVATE
    ${GSTREAMER_LIBRARIES}
    Threads::Threads
)
 
# Only the test entry point is separated.
if(BUILD_PLAYER_TEST)
    add_executable(player-test
        test/player_test.cpp
        ${PLAYER_COMMON_SOURCES}
    )
 
    target_include_directories(player-test PRIVATE
        ${CMAKE_CURRENT_SOURCE_DIR}
        ${GSTREAMER_INCLUDE_DIRS}
    )
 
    target_compile_options(player-test PRIVATE ${GSTREAMER_CFLAGS_OTHER})
    target_link_directories(player-test PRIVATE ${GSTREAMER_LIBRARY_DIRS})
    target_link_libraries(player-test PRIVATE
        ${GSTREAMER_LIBRARIES}
        Threads::Threads
    )
endif()
 
install(TARGETS video-player RUNTIME DESTINATION bin)
 
if(BUILD_PLAYER_TEST)
    install(TARGETS player-test RUNTIME DESTINATION bin)
endif()