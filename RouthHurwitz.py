import sympy as sp

class RouthHurwitz:
    def __init__(self, input_poly):
        self.input = input_poly

    def build_table(self):
        from sympy import symbols, Poly, sympify
        s = symbols('s')

        poly_expr = sympify(self.input)
        poly = Poly(poly_expr, s)
        coeffs = poly.all_coeffs()  
        
        rows = len(coeffs)
        cols = (len(coeffs) + 1) // 2

        routh_table = [[0.0 for _ in range(cols)] for _ in range(rows)]

        for i in range(0, len(coeffs), 2):
            routh_table[0][i // 2] = coeffs[i]
        
        for i in range(1, len(coeffs), 2):
            routh_table[1][i // 2] = coeffs[i]

        
        for i in range(2, rows):
            for j in range(cols - 1):
                
                a=routh_table[i-2][0]
                b=routh_table[i-2][j+1]
                c=routh_table[i-1][0]
                d=routh_table[i-1][j+1]
                denominator = c
                if denominator != 0:
                    c = ((b*c) -(a*d)) / denominator
                # check if c = 0
                
                routh_table[i][j] = c

            # chek if all row = 0
        print("Routh-Hurwitz Table:")
        for row in routh_table:
            print(row)

        self.check_stability(routh_table)

    def check_stability(self, routh_table):
        stable = True
        is_pos = routh_table[0][0] > 0

        for row in routh_table:
            if row[0] < 0 and is_pos or row[0] > 0 and not is_pos:
                stable = False
                print("Unstable")
                # call get_roots
                break

        if stable:
            print("Stable")

# Test case
rh = RouthHurwitz("s**3 + 1*s**2 + 2*s + 24")
rh.build_table()
