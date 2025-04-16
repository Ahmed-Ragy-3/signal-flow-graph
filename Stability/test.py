import unittest
from RouthHurwitz import RouthHurwitz

class TestRouthHurwitz(unittest.TestCase):
    def test_case_1(self):
        rh = RouthHurwitz("s**3 + 1*s**2 + 2*s + 24")
        self.assertEqual(rh.solve(), (2, "System is unstable"))

    def test_case_2(self):
        rh = RouthHurwitz("s**4 + 6*s**3 + 13*s**2 + 12*s + 4")
        self.assertEqual(rh.solve(), (0, "System is stable"))

    def test_case_3(self):
        rh = RouthHurwitz("1*s**4 + 2*s**3 + 3*s**2 + 4*s + 5")
        self.assertEqual(rh.solve(), (2, "System is unstable"))

    def test_case_4(self):
        rh = RouthHurwitz("1*s**5 + 7*s**4 + 6*s**3 + 42*s**2 + 8*s + 56")
        self.assertEqual(rh.solve(), (0, "System is marginally stable"))

    def test_case_5(self):
        rh = RouthHurwitz("1*s**5 + 2*s**4 + 3*s**3 + 6*s**2 + 5*s + 3")
        self.assertEqual(rh.solve(), (2, "System is unstable"))

    def test_case_6(self):
        rh = RouthHurwitz("1*s**5 + 2*s**4 + 2*s**3 + 4*s**2 + 11*s + 10")
        self.assertEqual(rh.solve(), (2, "System is unstable"))

    def test_case_7(self):
        rh = RouthHurwitz("s**4 - 1")
        self.assertEqual(rh.solve(), (1, "System is unstable"))

    def test_case_8(self):
        rh = RouthHurwitz("s**5 + s**4 + 2*s**3 + 2*s**2 + s + 1")
        self.assertEqual(rh.solve(), (0, "System is marginally stable"))

    def test_case_9(self):
        rh = RouthHurwitz("s**5 + s**4 + 2*s**3 + 2*s**2 + 3*s + 15")
        self.assertEqual(rh.solve(), (2, "System is unstable"))

if __name__ == '__main__':
    unittest.main()
