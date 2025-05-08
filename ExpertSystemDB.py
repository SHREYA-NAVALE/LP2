import sqlite3

# Step 1: Connect to (or create) database
conn = sqlite3.connect("medical.db")
cursor = conn.cursor()

# Step 2: Create table if not exists
cursor.execute('''
    CREATE TABLE IF NOT EXISTS diseases (
        symptom TEXT,
        disease TEXT
    )
''')

# Step 3: Insert sample data (only if table is empty)
cursor.execute("SELECT COUNT(*) FROM diseases")
if cursor.fetchone()[0] == 0:
    data = [
        ('fever', 'Flu'),
        ('headache', 'Flu'),
        ('body ache', 'Flu'),
        ('cough', 'COVID-19'),
        ('shortness of breath', 'COVID-19'),
        ('fever', 'COVID-19'),
        ('headache', 'Migraine'),
        ('nausea', 'Migraine'),
        ('abdominal pain', 'Food Poisoning'),
        ('nausea', 'Food Poisoning'),
        ('vomiting', 'Food Poisoning')
    ]
    cursor.executemany("INSERT INTO diseases (symptom, disease) VALUES (?, ?)", data)
    conn.commit()

# Step 4: Take user symptoms input
user_input = input("Enter your symptoms (comma-separated): ").lower()
symptoms = [s.strip() for s in user_input.split(",")]

# Step 5: Query diseases associated with the input symptoms
diseases_found = set()  # Use set to avoid duplicates

for symptom in symptoms:
    cursor.execute("SELECT disease FROM diseases WHERE symptom = ?", (symptom,))
    results = cursor.fetchall()
    for row in results:
        diseases_found.add(row[0])

# Step 6: Display the diseases found
if diseases_found:
    print("\nPossible Diseases based on your symptoms:")
    for disease in diseases_found:
        print(f"- {disease}")
else:
    print("No matching diseases found. Please consult a medical professional.")

conn.close()
