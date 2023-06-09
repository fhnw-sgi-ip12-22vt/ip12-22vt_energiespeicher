/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.github.mbelling.ws281x.jni;

public class rpi_ws281xJNI {
    public final static native int WS2811_TARGET_FREQ_get();

    public final static native int SK6812_STRIP_RGBW_get();

    public final static native int SK6812_STRIP_RBGW_get();

    public final static native int SK6812_STRIP_GRBW_get();

    public final static native int SK6812_STRIP_GBRW_get();

    public final static native int SK6812_STRIP_BRGW_get();

    public final static native int SK6812_STRIP_BGRW_get();

    public final static native int SK6812_SHIFT_WMASK_get();

    public final static native int WS2811_STRIP_RGB_get();

    public final static native int WS2811_STRIP_RBG_get();

    public final static native int WS2811_STRIP_GRB_get();

    public final static native int WS2811_STRIP_GBR_get();

    public final static native int WS2811_STRIP_BRG_get();

    public final static native int WS2811_STRIP_BGR_get();

    public final static native int WS2812_STRIP_get();

    public final static native int SK6812_STRIP_get();

    public final static native int SK6812W_STRIP_get();

    public final static native void ws2811_channel_t_gpionum_set(long jarg1, ws2811_channel_t jarg1_, int jarg2);

    public final static native int ws2811_channel_t_gpionum_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_invert_set(long jarg1, ws2811_channel_t jarg1_, int jarg2);

    public final static native int ws2811_channel_t_invert_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_count_set(long jarg1, ws2811_channel_t jarg1_, int jarg2);

    public final static native int ws2811_channel_t_count_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_strip_type_set(long jarg1, ws2811_channel_t jarg1_, int jarg2);

    public final static native int ws2811_channel_t_strip_type_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_leds_set(long jarg1, ws2811_channel_t jarg1_, long jarg2);

    public final static native long ws2811_channel_t_leds_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_brightness_set(long jarg1, ws2811_channel_t jarg1_, short jarg2);

    public final static native short ws2811_channel_t_brightness_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_wshift_set(long jarg1, ws2811_channel_t jarg1_, short jarg2);

    public final static native short ws2811_channel_t_wshift_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_rshift_set(long jarg1, ws2811_channel_t jarg1_, short jarg2);

    public final static native short ws2811_channel_t_rshift_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_gshift_set(long jarg1, ws2811_channel_t jarg1_, short jarg2);

    public final static native short ws2811_channel_t_gshift_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_bshift_set(long jarg1, ws2811_channel_t jarg1_, short jarg2);

    public final static native short ws2811_channel_t_bshift_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native void ws2811_channel_t_gamma_set(long jarg1, ws2811_channel_t jarg1_, long jarg2);

    public final static native long ws2811_channel_t_gamma_get(long jarg1, ws2811_channel_t jarg1_);

    public final static native long new_ws2811_channel_t();

    public final static native void delete_ws2811_channel_t(long jarg1);

    public final static native void ws2811_t_render_wait_time_set(long jarg1, ws2811_t jarg1_,
                                                                  java.math.BigInteger jarg2);

    public final static native java.math.BigInteger ws2811_t_render_wait_time_get(long jarg1, ws2811_t jarg1_);

    public final static native void ws2811_t_device_set(long jarg1, ws2811_t jarg1_, long jarg2);

    public final static native long ws2811_t_device_get(long jarg1, ws2811_t jarg1_);

    public final static native void ws2811_t_rpi_hw_set(long jarg1, ws2811_t jarg1_, long jarg2);

    public final static native long ws2811_t_rpi_hw_get(long jarg1, ws2811_t jarg1_);

    public final static native void ws2811_t_freq_set(long jarg1, ws2811_t jarg1_, long jarg2);

    public final static native long ws2811_t_freq_get(long jarg1, ws2811_t jarg1_);

    public final static native void ws2811_t_dmanum_set(long jarg1, ws2811_t jarg1_, int jarg2);

    public final static native int ws2811_t_dmanum_get(long jarg1, ws2811_t jarg1_);

    public final static native void ws2811_t_channel_set(long jarg1, ws2811_t jarg1_, long jarg2,
                                                         ws2811_channel_t jarg2_);

    public final static native long ws2811_t_channel_get(long jarg1, ws2811_t jarg1_);

    public final static native long new_ws2811_t();

    public final static native void delete_ws2811_t(long jarg1);

    public final static native int WS2811_SUCCESS_get();

    public final static native int WS2811_ERROR_GENERIC_get();

    public final static native int WS2811_ERROR_OUT_OF_MEMORY_get();

    public final static native int WS2811_ERROR_HW_NOT_SUPPORTED_get();

    public final static native int WS2811_ERROR_MEM_LOCK_get();

    public final static native int WS2811_ERROR_MMAP_get();

    public final static native int WS2811_ERROR_MAP_REGISTERS_get();

    public final static native int WS2811_ERROR_GPIO_INIT_get();

    public final static native int WS2811_ERROR_PWM_SETUP_get();

    public final static native int WS2811_ERROR_MAILBOX_DEVICE_get();

    public final static native int WS2811_ERROR_DMA_get();

    public final static native int WS2811_ERROR_ILLEGAL_GPIO_get();

    public final static native int WS2811_ERROR_PCM_SETUP_get();

    public final static native int WS2811_ERROR_SPI_SETUP_get();

    public final static native int WS2811_ERROR_SPI_TRANSFER_get();

    public final static native int ws2811_init(long jarg1, ws2811_t jarg1_);

    public final static native void ws2811_fini(long jarg1, ws2811_t jarg1_);

    public final static native int ws2811_render(long jarg1, ws2811_t jarg1_);

    public final static native int ws2811_wait(long jarg1, ws2811_t jarg1_);

    public final static native String ws2811_get_return_t_str(int jarg1);

    public final static native void ws2811_set_custom_gamma_factor(long jarg1, ws2811_t jarg1_, double jarg2);

    public final static native long ws2811_led_get(long jarg1, ws2811_channel_t jarg1_, int jarg2);

    public final static native int ws2811_led_set(long jarg1, ws2811_channel_t jarg1_, int jarg2, long jarg3);

    public final static native long ws2811_channel_get(long jarg1, ws2811_t jarg1_, int jarg2);
}
