SPACE = ' '
HYPHEN = '-'

def is_isogram(string):
    """
    Determine if a word or phrase is an isogram.

    An isogram (also known as a "nonpattern word") is a word or phrase without a repeating letter, however spaces and hyphens are allowed to appear multiple times.
    """

    normalized_str = string.replace(SPACE, '').replace(HYPHEN, '').lower()

    return len(normalized_str) == len(set(normalized_str))