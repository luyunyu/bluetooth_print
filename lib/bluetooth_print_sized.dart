

class FontSized {

  String font;

  int mul;

  FontSized._(this.font, this.mul);

  // TSS16.BF2简体中文16×16（GB码）
  // TSS20.BF2简体中文20×20（GB码）
  // TSS24.BF2简体中文24×24（GB码）
  // TSS32.BF2简体中文32×32（GB码）

  static final FontSized size_16 = FontSized._('TSS16.BF2', 1);
  static final FontSized size_20 = FontSized._('TSS20.BF2', 1);
  static final FontSized size_24 = FontSized._('TSS24.BF2', 1);
  static final FontSized size_32_16x2 = FontSized._('TSS16.BF2', 2);
  static final FontSized size_32 = FontSized._('TSS32.BF2', 1);
  static final FontSized size_40_20x2 = FontSized._('TSS20.BF2', 2);
  static final FontSized size_48_16x3 = FontSized._('TSS16.BF2', 3);
  static final FontSized size_48_24x2 = FontSized._('TSS24.BF2', 2);
  static final FontSized size_60_20x3 = FontSized._('TSS20.BF2', 3);
  static final FontSized size_64_32x2 = FontSized._('TSS32.BF2', 2);
  static final FontSized size_72_24x3 = FontSized._('TSS24.BF2', 3);

}

