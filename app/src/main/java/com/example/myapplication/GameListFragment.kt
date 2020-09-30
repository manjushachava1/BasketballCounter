import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Game
import com.example.myapplication.GameListViewModel
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_save_scores.*

private const val TAG = "GameListFragment"

class GameListFragment : Fragment() {

    private lateinit var gameRecyclerView: RecyclerView
    private var adapter: GameAdapter? = GameAdapter(emptyList())

    private val gameListViewModel: GameListViewModel by lazy {
        ViewModelProviders.of(this).get(GameListViewModel::class.java)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "Total games: ${gameListViewModel.games.size}")
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)

        gameRecyclerView =
            view.findViewById(R.id.game_recycler_view) as RecyclerView
        gameRecyclerView.layoutManager = LinearLayoutManager(context)
        gameRecyclerView.adapter = adapter

        //updateUI()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameListViewModel.gameListLiveData.observe(
            viewLifecycleOwner,
            Observer { games ->
                games?.let {
                    Log.i(TAG, "Got crimes ${games.size}")
                    updateUI(games)
                }
            })
    }

    private fun updateUI(games: List<Game>){
        adapter = GameAdapter(games)
        gameRecyclerView.adapter = adapter
    }

    private inner class GameHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = itemView.findViewById(R.id.game_title)
        val dateTextView: TextView = itemView.findViewById(R.id.game_date)
        val scoreATextView: TextView = itemView.findViewById(R.id.game_scoreA)
        val scoreBTextView: TextView = itemView.findViewById(R.id.game_scoreB)
        val teamATextView: TextView = itemView.findViewById(R.id.game_teamA)
        val teamBTextView: TextView = itemView.findViewById(R.id.game_teamB)
        val teamImageView: ImageView = itemView.findViewById(R.id.teamIcon)

    }

    private inner class GameAdapter(var games: List<Game>)
        : RecyclerView.Adapter<GameHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : GameHolder {
            val view = layoutInflater.inflate(R.layout.list_item_game, parent, false)
            return GameHolder(view)
        }

        override fun getItemCount() = games.size

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = games[position]
            holder.apply {
                titleTextView.text = game.title
                dateTextView.text = game.date.toString()
                scoreATextView.text = game.scoreA.toString()
                scoreBTextView.text = game.scoreB.toString()
                teamATextView.text = game.teamA
                teamBTextView.text = game.teamB
                if (game.scoreA > game.scoreB) {
                    teamImageView.setImageResource(R.drawable.a_team_icon)
                } else {
                    teamImageView.setImageResource(R.drawable.b_team_icon)
                }
            }
        }
    }


    companion object {
        fun newInstance(): GameListFragment {
            return GameListFragment()
        }
    }
}

