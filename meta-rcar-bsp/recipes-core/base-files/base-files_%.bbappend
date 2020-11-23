require include/rcar-bsp-path-common.inc

do_install_append_rcar-gen3 () {
    echo "export LD_LIBRARY_PATH=\"\${LD_LIBRARY_PATH}:${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
}

inherit module-base

do_install_basefilesissue_append_rcar-gen3 () {
    # Yocto version and codename
    printf "${DISTRO_NAME} " >> ${D}${sysconfdir}/issue.e2

    distro_version_nodate=${@'${DISTRO_VERSION}'.replace('snapshot-${DATE}','snapshot').replace('${DATE}','')}
    printf "%s " $distro_version_nodate >> ${D}${sysconfdir}/issue.e2

    printf "(${DISTRO_CODENAME})" >> ${D}${sysconfdir}/issue.e2
    echo >> ${D}${sysconfdir}/issue.e2

    # Linux kernel
    printf "Linux kernel: ${KERNEL_VERSION}" >> ${D}${sysconfdir}/issue.e2
    echo >> ${D}${sysconfdir}/issue.e2

    # YBSP version
    printf "BSP Renesas version: ${BSP_RENESAS_VERSION}" >> ${D}${sysconfdir}/issue.e2
    echo >> ${D}${sysconfdir}/issue.e2
}
