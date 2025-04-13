from sympy import symbols, Poly, sympify, simplify
eps = symbols('ε')
s = symbols('s')

class RouthHurwitz:
    def __init__(self, input_poly):
        self.input = input_poly
        self.zero_row_flag = False
        poly_expr = sympify(self.input)
        poly = Poly(poly_expr, s)
        self.coeffs = poly.all_coeffs()
        self.rows = len(self.coeffs)
        self.cols = (len(self.coeffs) + 1) // 2
        self.routh_table = [[0.0 for _ in range(self.cols)] for _ in range(self.rows)]

    def solve(self):
        print("\nCoefficients:", self.coeffs)

        self.__build_table()

        self.__print_table()

        self.__check_stability()

        self.__get_roots_and_location()

    def __build_table(self):

        for i in range(0, len(self.coeffs), 2):
            self.routh_table[0][i // 2] = self.coeffs[i]
        
        for i in range(1, len(self.coeffs), 2):
            self.routh_table[1][i // 2] = self.coeffs[i]

        for i in range(2, self.rows):
            for j in range(self.cols - 1):
                if self.routh_table[i - 1][0] == 0:
                    if self.routh_table[i - 2][0] > 0:
                        self.routh_table[i - 1][0] = eps
                    else:
                        self.routh_table[i - 1][0] = -1 * eps

                c = self.routh_table[i - 1][0]
                a = self.routh_table[i - 2][0]
                b = self.routh_table[i - 2][j + 1]
                d = self.routh_table[i - 1][j + 1]

                element = ((b * c) - (a * d)) / c
                self.routh_table[i][j] = simplify(element)

            if all(v == 0 for v in self.routh_table[i]):
                self.zero_row_flag = True
                # print(f"Row {i+1} is all zeros. Differentiating previous.")
                self.routh_table[i] = self.__differentiate_polynomial(i)

    def __differentiate_polynomial(self, row_idx):
        coefficients = self.routh_table[row_idx - 1].copy()
        degree = self.rows - row_idx

        temp = coefficients[0]
        for i in range(len(coefficients)):
            coefficients[i] /= temp
        # print("aux eqn: ", coefficients)

        derivative = coefficients
        for i in range(len(coefficients)):
            if degree > 0:
                derivative[i] = (degree * coefficients[i])
            else:
                derivative[i] = 0
            degree -= 2

        # print("derivative: ", derivative)
        return derivative

    def __check_stability(self):
        stable = True
        subs_eps = lambda x: x.subs('ε', 1e-6) if x.has(symbols('ε')) else x
        is_pos = subs_eps(self.routh_table[0][0]) > 0

        for row in self.routh_table:
            val = subs_eps(row[0])
            if val < 0 and is_pos or val > 0 and not is_pos:
                stable = False
                print("System is unstable")
                break

        if stable and self.zero_row_flag:
            print("System is marginally stable")
        elif stable:
            print("System is stable")

    def __get_roots_and_location(self):
        rhs_roots = []
        jw_roots = []
        lhs_roots = []
        poly = Poly(self.coeffs, symbols('s'))
        roots_arr = poly.nroots()

        # print("\nRoots of the chs eqn:-")
        for i, root in enumerate(roots_arr):
            real_part = root.as_real_imag()[0]
            # print(f"Root {i + 1}: {root}")
            if real_part > 0:
                rhs_roots.append(root)
            elif real_part == 0:
                jw_roots.append(root)
            else:
                lhs_roots.append(root)

        print(f"\nThere is {len(rhs_roots)} poles in the RHS :- ")
        for element in rhs_roots:
            print(element)
        print(f"\nThere is {len(jw_roots)} poles in the JW-axis :- ")
        for element in jw_roots:
            print(element)
        print(f"\nThere is {len(lhs_roots)} poles in the LHS :- ")
        for element in lhs_roots:
            print(element)

    def __print_table(self):
        print("\nRouth Table:-")
        col_width = 35
        for i, row in enumerate(self.routh_table):
            s_label = f"s^{self.rows - i - 1}".ljust(4)
            row_str = " | ".join(str(elem).ljust(col_width) for elem in row)
            print(f"{s_label} | {row_str}")