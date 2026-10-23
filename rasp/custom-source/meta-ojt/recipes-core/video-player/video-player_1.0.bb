SUMMARY = "OJT Video Player Core"
DESCRIPTION = "GStreamer based Player Core with separate test executable"
LICENSE = "CLOSED"
 
FILESEXTRAPATHS:prepend := "${THISDIR}/source:"
 
SRC_URI = " \
    file://CMakeLists.txt \
    file://MediaInfo.hpp \
    file://MediaBackend.hpp \
    file://GStreamerBackend.hpp \
    file://GStreamerBackend.cpp \
    file://PlayerCore.hpp \
    file://PlayerCore.cpp \
    file://main.cpp \
    file://test/player_test.cpp \
    file://test01.mp4 \
    file://test02.mp4 \
    file://test03.mp4 \
"
 
S = "${WORKDIR}"
 
inherit cmake pkgconfig
 
EXTRA_OECMAKE += "-DBUILD_PLAYER_TEST=ON"
 
DEPENDS += "gstreamer1.0"
 
RDEPENDS:${PN} += " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
"
 
do_install:append() {
    install -d ${D}${datadir}/video-player
    install -m 0644 ${WORKDIR}/test01.mp4 ${D}${datadir}/video-player/test01.mp4
    install -m 0644 ${WORKDIR}/test02.mp4 ${D}${datadir}/video-player/test02.mp4
    install -m 0644 ${WORKDIR}/test03.mp4 ${D}${datadir}/video-player/test03.mp4
}

 
FILES_${PN} += " \
    ${datadir}/video-player/test01.mp4 \
    ${datadir}/video-player/test02.mp4 \
    ${datadir}/video-player/test03.mp4 \
"