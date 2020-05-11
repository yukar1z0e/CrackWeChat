
  const char features[] = {"\n"
"C_FEATURE:"
#if (__GNUC__ * 000 + __GNUC_MINOR__) >= 000
"0"
#else
"0"
#endif
"c_function_prototypes\n"
"C_FEATURE:"
#if (__GNUC__ * 000 + __GNUC_MINOR__) >= 000 && defined(__STDC_VERSION__) && __STDC_VERSION__ >= 000000L
"0"
#else
"0"
#endif
"c_restrict\n"
"C_FEATURE:"
#if (__GNUC__ * 000 + __GNUC_MINOR__) >= 000 && defined(__STDC_VERSION__) && __STDC_VERSION__ >= 000000L
"0"
#else
"0"
#endif
"c_static_assert\n"
"C_FEATURE:"
#if (__GNUC__ * 000 + __GNUC_MINOR__) >= 000 && defined(__STDC_VERSION__) && __STDC_VERSION__ >= 000000L
"0"
#else
"0"
#endif
"c_variadic_macros\n"

};

int main(int argc, char** argv) { (void)argv; return features[argc]; }
