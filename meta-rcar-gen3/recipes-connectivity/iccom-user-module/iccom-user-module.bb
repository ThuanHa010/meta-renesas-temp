DESCRIPTION = "Linux iccom library"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=442d4e9f738ff4d05ae6215ae20caa6c"

inherit features_check

PN = "iccom-user-module"
PR = "r0"

REQUIRED_DISTRO_FEATURES = "iccom"

SRC_URI = "file://linux_iccom_library.tar.gz"
S = "${WORKDIR}/linux_iccom_library"

SRC_URI:append = " \
    file://0001-iccom-lib-add-iccom-sample-test-app.patch \
"

COMPATIBLE_MACHINE = "salvator-x"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile() {
    cd ${S}/
    oe_runmake
}

do_install () {
    # Create destination folders
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -d ${D}${bindir}

    # Copy library
    install -m 0755 ${S}/out/libiccom.so.1.0 ${D}${libdir}

    # Create symbolic link
    cd ${D}${libdir}
    ln -sf libiccom.so.1.0 libiccom.so.1
    ln -sf libiccom.so.1 libiccom.so

    install -m 0644 ${S}/public/* ${D}${includedir}

    install -m 0755 ${S}/out/iccom-test ${D}${bindir}
    install -m 0755 ${S}/out/iccom-sample-app ${D}${bindir}
}

RPROVIDES:${PN} += "linux-iccomlib"

PACKAGES =+ "libiccom-test"

FILES:libiccom-test += " \
    ${includedir}/* \
    ${bindir}/iccom-test \
    ${bindir}/iccom-sample-app \
"