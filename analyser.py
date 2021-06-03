import pickle

import nltk
nltk.download('stopwords')
nltk.download('punkt')
nltk.download('wordnet')
# from nltk.tokenize import word_tokenize, sent_tokenize


from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer



# Pickle files
model_filename = "temp_pickle_nbc.sav"
vectorizer_filename = "temp_vectorizer.pickle"

dictionary = {0: "Savings Account", 1: "Credit Card",
2: "Credit Reporting", 3: "Mortgage", 4: "Student Loan"}

def analyse(complaint):

	complaint = complaint.lower()

	# Input Cleaning
	stop_words = set(stopwords.words('english'))

	lemmatizer = WordNetLemmatizer()

	# New Input String After Lemmatization
	final_string = " ".join([lemmatizer.lemmatize(word) for word in complaint.split() if word not in stop_words])

	# Transform string before prediction
	vectorizer = pickle.load(open(vectorizer_filename, 'rb')) # load vectorizer pickle
	W = vectorizer.transform([final_string]) # final vector

	# Load Pickle file
	model = pickle.load((open(model_filename, "rb")))

	# Predict
	output = model.predict(W.toarray())[0]

	return dictionary[output]


