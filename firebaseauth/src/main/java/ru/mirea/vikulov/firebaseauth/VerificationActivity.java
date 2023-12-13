package ru.mirea.vikulov.firebaseauth;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.math.BigInteger;

public class VerificationActivity extends AppCompatActivity {
    private EditText verificationCodeEditText;
    private Button verifyButton;
    private Button backButton;
    private TextView hasht;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        mAuth = FirebaseAuth.getInstance();
        hasht = findViewById(R.id.hashV);
        verificationCodeEditText = findViewById(R.id.codeT);
        verifyButton = findViewById(R.id.confB);
        backButton = findViewById(R.id.backB);
        Intent intent = getIntent();
        String hashp = intent.getStringExtra("Pass");
        SHA512 SHA512 = new SHA512();
        String hash = SHA512.calculateSHA512(hashp);
        hasht.setText(hash);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String verificationCode = verificationCodeEditText.getText().toString();
                FirebaseUser user = mAuth.getCurrentUser();

                String horosho = verificationCodeEditText.getText().toString();
                if (horosho == null){
                    Toast.makeText(VerificationActivity.this,"Введите код", Toast.LENGTH_SHORT).show();
                }
                else {
                    SHA512 SHA512 = new SHA512();
                    String hash = SHA512.calculateSHA512(horosho);
                    hasht.setText(hash);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Возвращаемся обратно в MainActivity
            }
        });
    }
    public class SHA512 {

        public String main(String inputString) {
            String input = inputString;
            String sha512Hash = calculateSHA512(input);
            return sha512Hash;
        }

        private String calculateSHA512(String password) {
            // Инициализация переменных
            long[] h = {
                    0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL, 0xa54ff53a5f1d36f1L,
                    0x510e527fade682d1L, 0x9b05688c2b3e6c1fL, 0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L
            };

            // Массив констант k
            long[] k = {
                    0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL,
                    0x3956c25bf348b538L, 0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L,
                    0xd807aa98a3030242L, 0x12835b0145706fbeL, 0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L,
                    0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L, 0xc19bf174cf692694L,
                    0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L,
                    0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L,
                    0x983e5152ee66dfabL, 0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L,
                    0xc6e00bf33da88fc2L, 0xd5a79147930aa725L, 0x06ca6351e003826fL, 0x142929670a0e6e70L,
                    0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL, 0x53380d139d95b3dfL,
                    0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL,
                    0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L,
                    0xd192e819d6ef5218L, 0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L,
                    0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L, 0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L,
                    0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L, 0x682e6ff3d6b2b8a3L,
                    0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL,
                    0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL,
                    0xca273eceea26619cL, 0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L,
                    0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L, 0x113f9804bef90daeL, 0x1b710b35131c471bL,
                    0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL, 0x431d67c49c100d4cL,
                    0x4cc5d4becb3e42b6L, 0x597f299cfc657e2aL, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L
            };

            // Подготовка сообщения
            StringBuilder binarySB = new StringBuilder();
            for (char c : password.toCharArray()) {
                String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
                binarySB.append(binaryChar);
            }

            // Добавление 1-индикатора и дополнение нулями
            binarySB.append('1');
            Log.d(TAG, "binarysb: "+ binarySB);
            int currentLength = binarySB.length();
            //Определение количества незначащих нулей
            int additionalZeros = 1024 - (currentLength % 1024) - 128;
            //Определение наполнения 128 бит в конце с информацией о длине сообщения
            String bitRepresentation = String.format("%128s", Integer.toBinaryString(currentLength - 1)).replace(' ', '0');
            //3 штуки ниже - заполнение незначащими нулями и 128 битами и внесение их в строку сообщения
            for (int i = 0; i < additionalZeros; i++) {
                binarySB.append('0');
            }
            binarySB.append(bitRepresentation);


            // Формирование блоков и заполнение новых слов
            //for (int i = 0; i < binarySB.length(); i += 1024) {
//                int blockSize = Math.min(1024, binarySB.length() - i);
            long[] words = new long[80];

            // Первый цикл: конвертация подстроки в битовый массив и дополнение оставшихся элементов массива нулями
            for (int j = 0; j < 16; j++) {
                words[j] = Long.parseUnsignedLong(binarySB.substring( j * 64, (j + 1) * 64), 2);
            }

            // Второй цикл: заполнение новых слов в блоки
            for (int j = 16; j < 80; j++) {
                words[j] = sigma1(words[j - 2]) + words[j - 7] + sigma0(words[j - 15]) + words[j - 16];
            }

            // Сжатие
            long a = h[0], b = h[1], c = h[2], d = h[3], e = h[4], f = h[5], g = h[6], h0 = h[7];

            for (int j = 0; j < 80; j++) {
                long temp1 = h0 + Sigma1(e) + Ch(e, f, g) + k[j] + words[j];
                long temp2 = Sigma0(a) + Maj(a, b, c);

                h0 = g;
                g = f;
                f = e;
                e = addMod64( d + temp1);
                d = c;
                c = b;
                b = a;
                a = addMod64(temp1 + temp2);
            }

            // Обновление переменных
            long newH0 = h[0] + a;
            long newH1 = h[1] + b;
            long newH2 = h[2] + c;
            long newH3 = h[3] + d;
            long newH4 = h[4] + e;
            long newH5 = h[5] + f;
            long newH6 = h[6] + g;
            long newH7 = h[7] + h0;

            h[0] = newH0;
            h[1] = newH1;
            h[2] = newH2;
            h[3] = newH3;
            h[4] = newH4;
            h[5] = newH5;
            h[6] = newH6;
            h[7] = newH7;
            //}

            // Формирование хеша
            StringBuilder hashBuilder = new StringBuilder();
            for (long value : h) {
                hashBuilder.append(String.format("%016x", value));
            }

            return hashBuilder.toString();
        }

        private long Ch(long x, long y, long z) {
            return (x & y) ^ (~x & z);
        }

        private long Maj(long x, long y, long z) {
            return (x & y) ^ (x & z) ^ (y & z);
        }

        private long Sigma0(long x) {
            return rightRotate(x, 28) ^ rightRotate(x, 34) ^ rightRotate(x, 39);
        }

        private long Sigma1(long x) {
            return rightRotate(x, 14) ^ rightRotate(x, 18) ^ rightRotate(x, 41);
        }

        private long sigma0(long x) {
            return rightRotate(x, 1) ^ rightRotate(x, 8) ^ (x >>> 7);
        }

        private long sigma1(long x) {
            return rightRotate(x, 19) ^ rightRotate(x, 61) ^ (x >>> 6);
        }

        private long rightRotate(long value, int distance) {
            return (value >>> distance) | (value << (64 - distance));
        }
        public long addMod64(long... values) {
            BigInteger sum = BigInteger.ZERO;
            BigInteger modulo = BigInteger.ONE.shiftLeft(64);

            for (long value : values) {
                BigInteger bigInteger = BigInteger.valueOf(value);
                sum = sum.add(bigInteger);
            }

            return sum.mod(modulo).longValue();
        }

    }

}