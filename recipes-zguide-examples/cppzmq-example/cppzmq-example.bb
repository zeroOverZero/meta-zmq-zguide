DESCRIPTION = "ØMQ - The Guide, example C++ applications"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://github.com/imatix/zguide.git"

SRCREV = "c4656f3aa6f6a1d92957d53068a0af5e87c26c86"
SRC_URI = "git://github.com/imatix/zguide.git"
SRC_URI[md5sum] = "52dff4eb228f57dd9320affa23b4354e"
SRC_URI[sha256sum] = "88bf7e2bf767c7daa1a01242dde45091d84ffe710ca645c5db1c4e99f76864be"
PV = "4.3.2+git${SRCREV}"

S = "${WORKDIR}"
BUILDDIR = "${S}/git/examples/C++"

DEPENDS = "cppzmq"
LDFLAGS = "-lzmq -lpthread"
INCDIR = "${STAGING_DIR_TARGET}/usr/include"
LIBDIR = "${STAGING_DIR_TARGET}/usr/lib"

do_compile() {
	# Change to the c++ examples folder and build all examples.
	cd ${BUILDDIR}
	# Only build the files which contain a main function.
	for MAIN in `egrep -l "main \(" *.cpp`; do
		FILENAME=`echo ${MAIN} | cut -d"." -f1`
		${CXX} -O2 ${BUILDDIR}/${FILENAME}.cpp -o ${BUILDDIR}/${FILENAME} -I${INCDIR} -L${LIBDIR} ${LDFLAGS} -Wno-error=narrowing
	done
}

do_install() {
	# Install all applications into the /usr/bin directory.
	install -d ${D}${bindir}
	cd ${BUILDDIR}
	for MAIN in `egrep -l "main \(" *.cpp`; do
		FILENAME=`echo ${MAIN} | cut -d"." -f1`
		install -m 0755 ${BUILDDIR}/${FILENAME} ${D}${bindir}
	done
}
