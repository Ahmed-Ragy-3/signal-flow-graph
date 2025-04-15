from RouthHurwitz import RouthHurwitz
import re
def clean_polynomial_input(poly: str) -> str:
    poly = poly.replace(" ", "")
    poly = poly.replace("^", "**")
    poly = re.sub(r'(\d)(s)', r'\1*\2', poly)
    return poly

def main():
    print("\t\t\t\t\t\t\t=== Routh-Hurwitz Stability Checker ===")
    print("Enter a polynomial \nexample: (s**5 + s**4 + 10*s**3 + 72*s**2 + 152*s + 240) or (s^5 + s^4 + 10s^3 + 72s^2 + 152s + 240)")
    print("Type 'exit' to quit\n")

    while True:
        try:
            user_input = input("Enter the Characteristic equation: ")

            if user_input.lower() == "exit":
                print("Goodbye!")
                break

            formated = clean_polynomial_input(user_input)

            rh = RouthHurwitz(formated)
            rh.solve()

        except Exception as e:
            print(f"⚠️ Error: Invalid Syntax\nPlease try again.\n")

if __name__ == "__main__":
    main()
