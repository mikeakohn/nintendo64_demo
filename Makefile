INCLUDE_PATH=../naken_asm/include
JAVA_GRINDER=../java_grinder/java_grinder
NAKEN_ASM=../naken_asm/naken_asm
CLASSES= \
  BillionDevices.class \
  Bounce.class \
  Credits.class \
  Font.class \
  JavaKong.class \
  JavaTriangles.class \
  ManyTriangles.class \
  Matrix3D.class \
  Nintendo64Demo.class \
  Song.class \
  TitleScreen.class

default: $(CLASSES)
	$(JAVA_GRINDER) -v Nintendo64Demo.class nintendo64_demo.asm nintendo64
	$(NAKEN_ASM) -l -I $(INCLUDE_PATH) -type bin -o nintendo64_demo.z64 nintendo64_demo.asm
	./n64crc nintendo64_demo.z64

%.class: %.java
	javac -classpath ../java_grinder/build/JavaGrinder.jar:. $*.java

song:
	../drumsplusplus/playdpp -o assets/song.mid assets/song.dpp

rsp:
	cp ../naken_asm/samples/nintendo64/rsp.bin .

clean:
	@rm -f *.class *.hex *.asm *.lst *.z64

