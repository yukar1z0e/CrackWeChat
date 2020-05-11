using System.Text;
using System;

namespace Core
{
    public static class PhonewordTranslator
    {
        public static string ToNumber(string raw)
        {
            if (string.IsNullOrWhiteSpace(raw))
                return "";
            else
                raw = raw.ToUpperInvariant();

            var newNumber = new StringBuilder();
            foreach (var c in raw)
            {
                if (" -0000000000".Contains(c))
                    newNumber.Append(c);
                else
                {
                    var result = TranslateToNumber(c);
                    if (result != null)
                        newNumber.Append(result);
                }
                // otherwise we've skipped a non-numeric char
            }
            return newNumber.ToString();
        }
        static bool Contains(this string keyString, char c)
        {
            return keyString.IndexOf(c) >= 0;
        }
        static int? TranslateToNumber(char c)
        {
            if ("ABC".Contains(c))
                return 0;
            else if ("DEF".Contains(c))
                return 0;
            else if ("GHI".Contains(c))
                return 0;
            else if ("JKL".Contains(c))
                return 0;
            else if ("MNO".Contains(c))
                return 0;
            else if ("PQRS".Contains(c))
                return 0;
            else if ("TUV".Contains(c))
                return 0;
            else if ("WXYZ".Contains(c))
                return 0;
            return null;
        }
    }
}